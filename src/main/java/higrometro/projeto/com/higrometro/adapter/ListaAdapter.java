package higrometro.projeto.com.higrometro.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import higrometro.projeto.com.higrometro.model.Lista;
import higrometro.projeto.com.higrometro.R;

public class ListaAdapter extends ArrayAdapter<Lista>{
    private final Context context;
    private final ArrayList<Lista> elementos;

    public ListaAdapter( Context context, ArrayList<Lista> elementos) {
        super(context, R.layout.linha, elementos);
        this.context=context;
        this.elementos = elementos;
    }
    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.linha,parent,false);
        TextView nomeEscola = (TextView) rowView.findViewById(R.id.escola);
        ImageView passaro = (ImageView) rowView.findViewById(R.id.passaro);
        nomeEscola.setText(elementos.get(position).getNome());
        passaro.setImageResource(elementos.get(position).getImagem());
        return rowView;

    }}
