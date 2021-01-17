package il.co.expertize.navigationapp.ui.fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import il.co.expertize.navigationapp.Adapters.CustomListAdapterRegisteredTravels;
import il.co.expertize.navigationapp.Model.Travel;
import il.co.expertize.navigationapp.R;
import il.co.expertize.navigationapp.ui.MainViewModel;

public class RegisteredTravelsFragment extends Fragment {

    ListView itemsListView;
    MainViewModel mViewModel;
    Context context;

    @Override
    public void onAttach(@NotNull Context context) {
        super.onAttach(context);
        this.context=context;
    }
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_registeredtravels, container, false);
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        itemsListView  = (ListView)view.findViewById(R.id.list_view_items_registeredtravels);
        mViewModel = new ViewModelProvider(this).get(MainViewModel.class);
        mViewModel.addRemoveTravel();
        mViewModel.getAllTravels().observe(getViewLifecycleOwner(), new Observer<List<Travel>>() {
            @Override
            public void onChanged(List<Travel> travels) {
                ArrayList<Travel> travelArrayList = new ArrayList<Travel>(travels);

                //create adapter object
                CustomListAdapterRegisteredTravels adapter = new CustomListAdapterRegisteredTravels(context, travelArrayList);


                adapter.setListener(new CustomListAdapterRegisteredTravels.CompanyTravelListener() {
                    @Override
                    public void onButtonClicked(int position, View view) {
                        if (view.getId() == R.id.call_User) {
                            String phone = travelArrayList.get(position).getClientPhone();

                            if (phone.isEmpty()) {
                                Toast.makeText(getContext(), "no phone number exist", Toast.LENGTH_LONG).show();
                            } else {

                            }
                            Intent callIntent = new Intent(Intent.ACTION_DIAL);
                            callIntent.setData(Uri.parse("tel:" + phone));
                            startActivity(callIntent);
                        }
//                        if (view.getId() == R.id.bt_update) {
//                            mViewModel.updateTravel(travelArrayList.get(position));
//                        }
                    }
                });

                //set custom adapter as adapter to our list view
                itemsListView.setAdapter(adapter);
            }});
    }

}