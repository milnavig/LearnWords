package com.example.learnwords;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * <p>Класс Activity реализует страницу списка слов. </p>
 * <p>Содержит обработчики кнопок и полей</p>
 * @author Alex Tereschenko
 *
 * */
public class WordList extends AppCompatActivity {
    //ListView line;
    DBHelper dbHelper;
    Cursor userCursor;
    //SimpleCursorAdapter userAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word_list);

        addListenerOnButton();
    }

    /*
    public void addListenerOnButton() {
        line = (ListView)findViewById(R.id.list);

        dbHelper = new DBHelper(this);
        SQLiteDatabase dbdata = dbHelper.getWritableDatabase();
        //получаем данные из бд в виде курсора
        userCursor =  dbdata.rawQuery("select * from "+ dbHelper.TABLE_WORDS, null);
        // определяем, какие столбцы из курсора будут выводиться в ListView
        //System.out.println(text);
        String[] headers = new String[] {dbHelper.KEY_ID, dbHelper.KEY_WORD, dbHelper.KEY_TRANSLATION};
        // создаем адаптер, передаем в него курсор
        userAdapter = new SimpleCursorAdapter(this, android.R.layout.simple_list_item_2,
                userCursor, headers, new int[]{android.R.id.text1, android.R.id.text2}, 0);

        line.setAdapter(userAdapter);
    }*/

    private ListView listviewitems;
    private ItemAdapter adapteritem;
    private ArrayList<Word> arrListItems;

    public void addListenerOnButton() {
        listviewitems = (ListView) findViewById(R.id.list);
        arrListItems = getListItemData();

        //Fill data into listview.............
        if(arrListItems.size()>0)
        {
            adapteritem=new ItemAdapter(WordList.this, arrListItems);
            listviewitems.setAdapter(adapteritem);
        }
        else
        {

        }
    }

    private ArrayList<Word> getListItemData(){
        ArrayList<Word> listViewItems = new ArrayList<Word>();

        dbHelper = new DBHelper(this);
        SQLiteDatabase dbdata = dbHelper.getWritableDatabase();
        //получаем данные из бд в виде курсора
        userCursor =  dbdata.rawQuery("select * from "+ dbHelper.TABLE_WORDS, null);
        Integer num = userCursor.getCount();
        userCursor.moveToFirst();
        for (Integer i =0 ; i < num; i++) {
            listViewItems.add(new Word(Integer.parseInt(userCursor.getString(0)),userCursor.getString(1),userCursor.getString(2)));
            userCursor.moveToNext();
        }

        //listViewItems.add(new Word(1,"Rice","Test"));
        //listViewItems.add(new Word(2,"Beans","Test"));
        //listViewItems.add(new Word(3,"Yam","Test"));
        //listViewItems.add(new Word(4,"Pizza","Test"));
        //listViewItems.add(new Word(5,"Fries","Test"));

        return listViewItems;
    }

    public class ItemAdapter extends BaseAdapter {
        private LayoutInflater lyt_Inflater = null;

        private ArrayList<Word> arrlstitems;
        private Context context;

        public ItemAdapter(Context cnt,ArrayList<Word> items)
        {
            this.context = cnt;
            this.arrlstitems = items;
        }


        @Override
        public int getCount()
        {
            return arrlstitems.size();
        }

        @Override
        public Object getItem(int position)
        {
            return null;
        }

        @Override
        public long getItemId(int position)
        {
            return 0;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent)
        {
            View view_lyt = convertView;
            try
            {
                if(arrlstitems.size()>0)
                {
                    final Word item = arrlstitems.get(position);
                    String wrd = item.getWord();
                    String wrd2 = item.getTranslation();

                    lyt_Inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                    view_lyt = lyt_Inflater.inflate(R.layout.item_my_word, null);

                    TextView txtnm=(TextView) view_lyt.findViewById(R.id.txtTitle);
                    TextView txtnm2=(TextView) view_lyt.findViewById(R.id.txtTitle2);
                    ImageButton imgbtnDelete=(ImageButton) view_lyt.findViewById(R.id.imgbtnDelete);
                    ImageButton imgbtnEdit=(ImageButton) view_lyt.findViewById(R.id.imgbtnEdit);
                    txtnm.setText(wrd);
                    txtnm2.setText(wrd2);
                /*
                if(isEditMode)
                {
                    imgbtnDelete.setVisibility(View.VISIBLE);
                }
                else
                {
                    imgbtnDelete.setVisibility(View.GONE);
                }*/

                    view_lyt.setOnClickListener(new View.OnClickListener()
                    {
                        @Override
                        public void onClick(View v)
                        {


                        }
                    });
                    imgbtnDelete.setOnClickListener(new View.OnClickListener()
                    {

                        @Override
                        public void onClick(View v)
                        {
                            if(arrlstitems.size()>0)
                                removeItemFromList(position);
                        }
                    });

                    imgbtnEdit.setOnClickListener(new View.OnClickListener()
                    {

                        @Override
                        public void onClick(View v)
                        {
                            if(arrlstitems.size()>0)
                                editItemFromList(position);
                        }
                    });

                }

            }
            catch (Exception e)
            {
                Log.i("Exception==", e.toString());
            }

            return view_lyt;
        }
    }

    // Method for remove Single item from list
    protected void removeItemFromList(int position)
    {
        final int deletePosition = position;

        AlertDialog.Builder builder = new AlertDialog.Builder(WordList.this);
        builder.setTitle("Alert!");
        builder.setMessage("Do you want delete this item?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener()
                {
                    public void onClick(DialogInterface dialog, int id)
                    {
                        //System.out.println(deletePosition);
                        Word el = arrListItems.get(deletePosition);
                        Integer el_id = el.getId();
                        dbHelper.deleteWord(el);

                        arrListItems.remove(deletePosition);
                        adapteritem.notifyDataSetChanged();
                        adapteritem.notifyDataSetInvalidated();

                        if(arrListItems.size()==0)
                        {

                        }
                    }
                })
                .setNegativeButton("No",new DialogInterface.OnClickListener()
                {
                    public void onClick(DialogInterface dialog,int id)
                    {
                        dialog.cancel();
                    }
                });
        AlertDialog  alertDialog = builder.create();
        alertDialog.show();
    }

    protected void editItemFromList(int position)
    {
        final int editPosition = position;
        Word el = arrListItems.get(editPosition);
        int el_id = el.getId();

        Intent intent = new Intent("com.example.learnwords.EditWord");
        intent.putExtra("id", el_id);
        startActivity(intent);
    }
}

