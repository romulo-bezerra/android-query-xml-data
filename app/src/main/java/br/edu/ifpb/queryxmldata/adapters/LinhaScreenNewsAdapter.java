package br.edu.ifpb.queryxmldata.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import br.edu.ifpb.queryxmldata.R;
import br.edu.ifpb.queryxmldata.valueObjects.Message;

public class LinhaScreenNewsAdapter extends ArrayAdapter<Message> {

    private final Context context;
    private final ArrayList<Message> elementos;

    public LinhaScreenNewsAdapter(Context context, ArrayList<Message> elementos) {
        super(context, R.layout.linha_screen_news, elementos);
        this.context= context;
        this.elementos= elementos;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.linha_screen_news, parent, false);

        TextView titulo = rowView.findViewById(R.id.tvTitulo);
        TextView link = rowView.findViewById(R.id.tvLink);

        titulo.setText(elementos.get(position).getTitle());
        link.setText(elementos.get(position).getLink().toString());

        return rowView;
    }

}
