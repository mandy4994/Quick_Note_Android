package com.ass1.mandeep.quicknote;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Mandeep Singh, 213347007
 * SIT207 Assignment 2
 */
public class NotesArrayAdapter extends ArrayAdapter<Note> {
    private final Context context;
    private final List<Note> values;
    public static final String PREFS_NAME = "AOP_PREFS";
    public static final String PREFS_KEY = "AOP_PREFS_String";
    public NotesArrayAdapter(Context context, List<Note>values) {
        super(context, R.layout.rowlayout, values);
        this.context = context;
        this.values = values;
    }
    @Override public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.rowlayout, parent, false);
        TextView TxtName = (TextView) rowView.findViewById(R.id.lblName);
        TextView TxtContent = (TextView) rowView.findViewById(R.id.lblContent);
        TextView TxtDate = (TextView) rowView.findViewById(R.id.lblDate);
        CheckBox check = (CheckBox)rowView.findViewById(R.id.checkbox);
        int size = CheckSettings();
        if(size != -1)
        {
            TxtName.setTextSize(size);
            TxtContent.setTextSize((0.6f * size));
            TxtDate.setTextSize((0.6f * size));
        }
        TxtName.setText(values.get(position).noteTitle);
        TxtContent.setText(values.get(position).contShort);
        TxtDate.setText(values.get(position).NoteDate);




        return rowView;
    }

    private int CheckSettings()
    {
        int size = -1;
        SharedPreferences settings;
        settings = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE); //1
        size = settings.getInt(PREFS_KEY, -1);
        return size;
    }
}
