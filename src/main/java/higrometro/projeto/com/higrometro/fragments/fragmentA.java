package higrometro.projeto.com.higrometro.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import higrometro.projeto.com.higrometro.R;
import higrometro.projeto.com.higrometro.helper.ConfiguracaoFirebase;

public class fragmentA extends Fragment{
    View view;
    private DatabaseReference firebase,firebase1;
    private ArrayList<String> dados = new ArrayList<>();
    private ArrayAdapter<String> adapter;
    private ListView lista;
    private SearchView sv;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment1,container,false);
        lista = (ListView) view.findViewById(R.id.list_umid);
        sv = (SearchView) view.findViewById(R.id.searchView1);
        adapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_dropdown_item_1line,dados);
        lista.setAdapter(adapter);
        sv.setQueryHint("Search..");
        firebase = ConfiguracaoFirebase.getFirebase().child("HumidityList");
        firebase.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded( DataSnapshot dataSnapshot, @Nullable String s) {
                    if(dataSnapshot.exists())
                    {
                        SimpleDateFormat SDF = new SimpleDateFormat("dd/MM/yyyy");
                        SimpleDateFormat SDF1 = new SimpleDateFormat("hh:mm:ss");
                        String date ="Ano: "+ SDF.format(new Date())+" Horário: "+ SDF1.format(new Date());
                        firebase1 = ConfiguracaoFirebase.getFirebase();
                        firebase1.child("Date").setValue(date);
                        String value = dataSnapshot.getValue().toString();
                        dados.add(value);
                        adapter.notifyDataSetChanged();
                    }else
                        {
                            firebase.removeEventListener(this);
                        }
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved( DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved( DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled( DatabaseError databaseError) {

            }
        });
        sv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String txt) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String txt) {
                adapter.getFilter().filter(txt);

                return false;
            }
        });



        return view;
    }
    @Override
    public void onStop() {
        adapter.clear();
        super.onStop();
    }


    }

