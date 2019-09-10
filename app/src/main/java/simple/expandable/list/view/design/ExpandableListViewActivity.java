package simple.expandable.list.view.design;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.HashMap;

public class ExpandableListViewActivity extends AppCompatActivity {

    private ArrayList<GroupModel> arrayList;
    private HashMap<String, GroupModel> listHashMap;

    private ExpandableListViewAdapter expandableListViewAdapter;
    private ExpandableListView expandableListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expandable_list_view);
        // initializing the views
        initViews();

        // initializing the listeners
        initListeners();

        // initializing the objects
        initObjects();

        // preparing list data
        initListData();

        //expand all the Groups
        expandAll();
    }

    /**
     * method to initialize the views
     */
    private void initViews() {
        expandableListView = (ExpandableListView) findViewById(R.id.expandable_list_view);
    }

    /**
     * method to initialize the listeners
     */
    private void initListeners() {

        // ExpandableListView on child click listener
        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {

            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                GroupModel groupModel = arrayList.get(groupPosition);
                ChildModel  childModel =  groupModel.getChildArrayList().get(childPosition);
                Toast.makeText(getBaseContext(), " Clicked on :: "+groupModel.getGroupName()+"/"+((ChildModel) childModel).getChildName(), Toast.LENGTH_LONG).show();
                return false;
            }
        });

        // ExpandableListView on group click listener
        expandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                GroupModel groupModel = arrayList.get(groupPosition);
                Toast.makeText(getBaseContext(), " Header is :: "+groupModel.getGroupName(),Toast.LENGTH_LONG).show();
                return false;
            }
        });

        // ExpandableListView Group expanded listener
        expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {

            @Override
            public void onGroupExpand(int groupPosition) {
                GroupModel groupModel = arrayList.get(groupPosition);
                Toast.makeText(getApplicationContext(), groupModel.getGroupName() + " Expanded", Toast.LENGTH_SHORT).show();
            }
        });

        // ExpandableListView Group collapsed listener
        expandableListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {

            @Override
            public void onGroupCollapse(int groupPosition) {
                GroupModel groupModel = arrayList.get(groupPosition);
                Toast.makeText(getApplicationContext(), groupModel.getGroupName() + " Collapsed", Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * method to initialize the objects
     */
    private void initObjects() {
        arrayList = new ArrayList<GroupModel>();
        listHashMap = new HashMap<String, GroupModel>();
        // initializing the adapter object
        expandableListViewAdapter = new ExpandableListViewAdapter(ExpandableListViewActivity.this, arrayList);
        // setting list adapter
        expandableListView.setAdapter(expandableListViewAdapter);
    }

    /*
     * Preparing the list data
     *
     */
    private void initListData() {
        prepareListData("Group 1","Child 1");
        prepareListData("Group 1","Child 2");
        prepareListData("Group 1","Child 3");
        prepareListData("Group 1","Child 4");
        prepareListData("Group 1","Child 5");
        prepareListData("Group 1","Child 6");
        prepareListData("Group 2","Child 7");
        prepareListData("Group 2","Child 8");
        prepareListData("Group 2","Child 9");
        prepareListData("Group 3","Child 10");
        prepareListData("Group 3","Child 11");
        prepareListData("Group 3","Child 12");
        prepareListData("Group 3","Child 13");
        prepareListData("Group 4","Child 14");
        prepareListData("Group 4","Child 15");
        prepareListData("Group 5","Child 16");
        prepareListData("Group 5","Child 17");
        prepareListData("Group 5","Child 18");
        prepareListData("Group 6","Child 19");
        prepareListData("Group 6","Child 20");

        // notify the adapter
        expandableListViewAdapter.notifyDataSetChanged();
        expandableListViewAdapter.notifyDataSetInvalidated();
    }

    /*
     * Expand all groups
     *
     */
    private void expandAll() {
        int count = expandableListViewAdapter.getGroupCount();
        for (int i = 0; i < count; i++){
            expandableListView.expandGroup(i);
        }
    }

    /*
     * Collapse all groups
     *
     */
    private void collapseAll() {
        int count =expandableListViewAdapter.getGroupCount();
        for (int i = 0; i < count; i++){
            expandableListView.collapseGroup(i);
        }
    }

    /*
     * Preparing the data
     */
    private void prepareListData(String firstLevel,String secondLevel)
    {
        /*
         * check the hash map if the group already exists
         */
        GroupModel groupModel = listHashMap.get(firstLevel);

        /*
         * add the group if doesn't exists
         */
        if(groupModel == null)
        {
            groupModel = new GroupModel();
            groupModel.setGroupName(firstLevel);
            listHashMap.put(firstLevel, groupModel);
            arrayList.add(groupModel);
        }

        /*
         * get the children for the group
         */
        ArrayList<ChildModel> productList = groupModel.getChildArrayList();

        /*
         * create a new child and add that to the group
         */
        ChildModel childModel = new ChildModel();
        childModel.setChildName(secondLevel);
        productList.add(childModel);
        groupModel.setChildArrayList(productList);
    }
}