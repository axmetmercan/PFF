package com.example.pff;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class messagesRecyclerView extends RecyclerView.Adapter<messagesRecyclerView.MessagesHolder> {

    private ArrayList<Message> messageArrayList;

    public messagesRecyclerView(ArrayList<Message> messageArrayList) {
        this.messageArrayList = messageArrayList;
    }

    public ArrayList<Message> getMessageArrayList() {
        return messageArrayList;
    }

    public void setMessageArrayList(ArrayList<Message> messageArrayList) {
        this.messageArrayList = messageArrayList;
    }

    @NonNull
    @Override
    public MessagesHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.messages_row_layout,parent, false);
        MessagesHolder holder = new MessagesHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MessagesHolder holder, int position) {
        holder.name.setText(messageArrayList.get(position).getUserName());
        holder.phone.setText(messageArrayList.get(position).getPhoneNumber());
        holder.petName.setText(messageArrayList.get(position).getPetName());
        Picasso.get().load(messageArrayList.get(position).getImgUrl()).into(holder.imageView);
        holder.open.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.parse("smsto:"+messageArrayList.get(position).getPhoneNumber()));
//                intent.setData();  // This ensures only SMS apps respond
                intent.putExtra("sms_body","Hi, again ");
//                intent.putExtra(Intent.EXTRA_STREAM, attachment);
                holder.context.startActivity(intent);


            }
        });

    }


    @Override
    public int getItemCount() {
        return messageArrayList.size();
    }


    public class MessagesHolder extends RecyclerView.ViewHolder{

        TextView name, phone, petName;
        ImageView imageView;
        Button open;
        Context context;

        public MessagesHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.txtMsgUserName);
            phone = itemView.findViewById(R.id.txtMessageUserPhone);
            petName = itemView.findViewById(R.id.txtUserPetName);
            imageView = itemView.findViewById(R.id.icPetMessage);
            open = itemView.findViewById(R.id.btnSendMessage);
            context = itemView.getContext();

        }
    }
}
