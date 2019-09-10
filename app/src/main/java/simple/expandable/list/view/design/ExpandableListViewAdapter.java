package simple.expandable.list.view.design;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;
import java.util.ArrayList;

public class ExpandableListViewAdapter extends BaseExpandableListAdapter
{
    private ArrayList<GroupModel> groupModelArrayList;
    private LayoutInflater layoutInflater;

    public ExpandableListViewAdapter(Context context, ArrayList<GroupModel> arrayList)
    {
        this.groupModelArrayList = arrayList;
        this.layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition)
    {
        ArrayList<ChildModel> childModelArrayList = groupModelArrayList.get(groupPosition).getChildArrayList();
        return childModelArrayList.get(childPosition);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition)
    {
        return childPosition;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent)
    {
        ChildViewHolder childViewHolder;

        if (convertView == null)
        {
            convertView = layoutInflater.inflate(R.layout.child_item, parent, false);
            childViewHolder = new ChildViewHolder(convertView);
            convertView.setTag(childViewHolder);
        }
        else
        {
            childViewHolder = (ChildViewHolder) convertView.getTag();
        }

        final ChildModel childModelPosition = (ChildModel) getChild(groupPosition, childPosition);
        ((ChildViewHolder) childViewHolder).setData(childModelPosition);
        return convertView;
    }

    @Override
    public int getChildrenCount(int groupPosition)
    {
        ArrayList<ChildModel> childModelArrayList = groupModelArrayList.get(groupPosition).getChildArrayList();
        return childModelArrayList.size();
    }

    @Override
    public Object getGroup(int groupPosition)
    {
        return groupModelArrayList.get(groupPosition);
    }

    @Override
    public int getGroupCount()
    {
        return groupModelArrayList.size();
    }

    @Override
    public long getGroupId(int groupPosition)
    {
        return groupPosition;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isLastChild, View convertView, ViewGroup parent)
    {
        GroupViewHolder groupViewHolder;

        if (convertView == null)
        {
            convertView = layoutInflater.inflate(R.layout.group_item, parent, false);
            groupViewHolder = new GroupViewHolder(convertView);
            convertView.setTag(groupViewHolder);
        }
        else
        {
            groupViewHolder = (GroupViewHolder) convertView.getTag();
        }

        GroupModel groupModelPosition = (GroupModel) getGroup(groupPosition);
        ((GroupViewHolder) groupViewHolder).setData(groupModelPosition);
        return convertView;
    }

    @Override
    public boolean hasStableIds()
    {
        return true;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition)
    {
        return true;
    }

    private class GroupViewHolder
    {
        TextView groupTextView;

        public GroupViewHolder(View item)
        {
            groupTextView = (TextView) item.findViewById(R.id.group_text_view);
        }

        public void setData(GroupModel itemPosition)
        {
            groupTextView.setText(itemPosition.getGroupName());
        }
    }

    private class ChildViewHolder
    {
        TextView childTextView;

        public ChildViewHolder(View item)
        {
            childTextView = (TextView) item.findViewById(R.id.child_text_view);
        }

        public void setData(ChildModel itemPosition)
        {
            childTextView.setText(itemPosition.getChildName());
        }
    }
}
