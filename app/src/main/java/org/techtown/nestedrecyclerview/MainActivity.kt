package org.techtown.nestedrecyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.zip.Inflater

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val chatList = mutableListOf<Chat>()
        chatList.add(Chat("안녕하세요",false))
        chatList.add(Chat("안녕하세요",true))
        chatList.add(Chat("안녕하세요",false))
        chatList.add(Chat("안녕하세요",false))
        chatList.add(Chat("안녕하세요",true))
        chatList.add(Chat("안녕하세요",false))
        chatList.add(Chat("안녕하세요",false))
        chatList.add(Chat("안녕하세요",false))
        chatList.add(Chat("안녕하세요",true))

        val adapter = ChatRecyclerAdapter(
            chatList = chatList,
            inflater = LayoutInflater.from(this@MainActivity)
        )

        with(findViewById<RecyclerView>(R.id.chatRecyclerView)) {
            this.layoutManager = LinearLayoutManager(this@MainActivity)
            this.adapter = adapter
        }

        findViewById<Button>(R.id.button).setOnClickListener {
            adapter.chatList.add(2, Chat("두번째 입니다",false))
            adapter.notifyItemInserted(2)
        }

        findViewById<Button>(R.id.button2).setOnClickListener {
            adapter.chatList.add(2, Chat("세번째 입니다",true))
            adapter.notifyItemInserted(2)
        }

    }
}

class ChatRecyclerAdapter(
    val chatList: MutableList<Chat>,
    val inflater: LayoutInflater
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    inner class RightViewHolder(itemView: View) :RecyclerView.ViewHolder(itemView) {
        val rightTextVew : TextView

        init {
            rightTextVew = itemView.findViewById(R.id.chatTextRight)
        }
    }

    inner class LeftViewHolder(itemView: View) :RecyclerView.ViewHolder(itemView) {
        val leftTextVew : TextView

        init {
            leftTextVew = itemView.findViewById(R.id.chatTextLeft)
        }
    }

    override fun getItemViewType(position: Int): Int {
        when(chatList.get(position).is_right) {
            true -> return 1
            false -> return 0
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        when(viewType) {
            1 -> {
                return RightViewHolder(inflater.inflate(R.layout.chatitem_right, parent,false))
            }
            else -> {
                return LeftViewHolder(inflater.inflate(R.layout.chatitem_left, parent,false))
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val chat = chatList.get(position)
        when(chat.is_right) {
            true -> (holder as RightViewHolder).rightTextVew.text = chat.message
            false -> (holder as LeftViewHolder).leftTextVew.text = chat.message
        }
    }

    override fun getItemCount(): Int {
        return chatList.size
    }

}


class Chat(val message: String, val is_right: Boolean)











































