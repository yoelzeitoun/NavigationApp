package il.co.expertize.navigationapp.Adapters;

import android.content.Context;
import android.location.Geocoder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

import il.co.expertize.navigationapp.Model.Travel;
import il.co.expertize.navigationapp.R;

public class CustomListAdapterCompanyTravels extends BaseAdapter {
    private final Context context;
    private final ArrayList<Travel> items;
    private CompanyTravelListener listener;

    public CustomListAdapterCompanyTravels(Context context, ArrayList<Travel> items) {
        this.context = context;
        this.items = items;
    }

    public interface CompanyTravelListener {
        void onButtonClicked(int position, View view);
    }

    public void setListener(CustomListAdapterCompanyTravels.CompanyTravelListener listener){
        this.listener=listener;
    }

    @Override
    public int getCount() {
        return items.size(); //returns total item in the list
    }

    @Override
    public Object getItem(int position) {
        return items.get(position); //returns the item at the specified position
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        CustomListAdapterCompanyTravels.ViewHolder viewHolder;

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.rowitem_travelscompany, parent, false);
            viewHolder = new CustomListAdapterCompanyTravels.ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else
        {
            viewHolder = (CustomListAdapterCompanyTravels.ViewHolder) convertView.getTag();
        }

        Travel currentItem = (Travel) getItem(position);
        viewHolder.clientName.setText(currentItem.getClientName());
        viewHolder.numOfPassengers.setText(currentItem.getNumOfPassengers());
        viewHolder.travelLocation.setText(Travel.UserLocationConverter.asString(currentItem.getTravelLocation()));
        viewHolder.destinationAddress.setText(currentItem.getDestinationAddress());

        //viewHolder.call.setTag(R.integer.call_view, convertView);
        viewHolder.takeTravel.setTag(R.integer.taketravel_pos, position);
        viewHolder.takeTravel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //    View tempview = (View) viewHolder.call.getTag(R.integer.call_view);
                Integer pos = (Integer) viewHolder.takeTravel.getTag(R.integer.taketravel_pos);
                if (listener!=null)
                    listener.onButtonClicked(pos,view);
            }
        });


        //  viewHolder.update.setTag(R.integer.update_view, convertView);
        viewHolder.call.setTag(R.integer.call_pos, position);
        viewHolder.call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //     View tempview = (View) viewHolder.update.getTag(R.integer.update_view);
                Integer pos = (Integer) viewHolder.call.getTag(R.integer.call_pos);
                if (listener!=null)
                    listener.onButtonClicked(pos,view);
            }
        });

        //  viewHolder.update.setTag(R.integer.update_view, convertView);
        viewHolder.sendEmail.setTag(R.integer.sendEmail_pos, position);
        viewHolder.sendEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //     View tempview = (View) viewHolder.update.getTag(R.integer.update_view);
                Integer pos = (Integer) viewHolder.sendEmail.getTag(R.integer.sendEmail_pos);
                if (listener!=null)
                    listener.onButtonClicked(pos,view);
            }
        });

        return convertView;
    }

    //ViewHolder inner class
    private static class ViewHolder {
        TextView clientName;
        TextView numOfPassengers;
        TextView travelLocation;
        TextView destinationAddress;

        Button sendEmail;
        Button call;
        Button takeTravel;

        public ViewHolder(View view) {
            clientName = (TextView)view.findViewById(R.id.clientName);
            numOfPassengers = (TextView) view.findViewById(R.id.num_of_passengers);
            travelLocation = (TextView) view.findViewById(R.id.address);
            destinationAddress = (TextView) view.findViewById(R.id.destination_address);

            sendEmail=(Button)view.findViewById(R.id.send_mail);
            call=(Button)view.findViewById(R.id.call_User);
            takeTravel=(Button)view.findViewById(R.id.validate_travel);
        }
    }
}
