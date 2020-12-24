//package com.example.bookyourbeauty;
//
//public class stam {
//    public class Options extends AppCompatActivity  implements View.OnClickListener{
//
//        ListView ViewP;
//        FirebaseDatabase rootNode;
//        DatabaseReference reference;
//        Button Pay;
//        ArrayList<String> arrayList=new ArrayList<>();
//        ArrayAdapter<String> arrayAdapter;
//        public String item;
//        public String id_of_business_item;
//        public String num_item;
//        public String id_of_client;
//
//
//        ArrayList<String> ChooseDel= new ArrayList<>();
//        ArrayList<String> ChoosePlace= new ArrayList<>();
//        ArrayList<String> ChoosePrice= new ArrayList<>();
//        ArrayList<String> ChooseCategories= new ArrayList<>();
//
//
//
//        public String id_func (String name,String search){
//            System.out.println("name= "+name);
//            int index_id=name.indexOf(search);
//            index_id+=search.length();
//            String answer="";
//            while(name.charAt(index_id)!=',' && index_id<name.length()-1 && name.charAt(index_id)!=' '){
//                System.out.print(name.charAt(index_id));
//                answer+=name.charAt(index_id);
//                index_id++;
//            }
//            System.out.println("id of busniess item is ????="+answer);
//            return answer;
//        }
//        @Override
//        protected void onCreate(Bundle savedInstanceState) {
//            Intent intent=getIntent();
//            ChooseCategories =intent.getExtras().getStringArrayList(("ChooseCategories"));
//            ChooseDel = intent.getExtras().getStringArrayList(("ChooseDel"));
//            ChoosePlace = intent.getExtras().getStringArrayList("ChoosePlace");
//            ChoosePrice = intent.getExtras().getStringArrayList("ChoosePrice");
//            id_of_client= intent.getExtras().getString("id");
//
//
//            rootNode = FirebaseDatabase.getInstance();
//            reference = rootNode.getReference("Products");
//            super.onCreate(savedInstanceState);
//            setContentView(R.layout.activity_options);
//            Pay= (Button) findViewById(R.id.Pay) ;
//
//            System.out.println("new page");
//            System.out.println("****************************"+ ChooseDel.toString());
//            System.out.println("****************************"+ChooseCategories.toString());
//            System.out.println("****************************"+ChoosePlace.toString());
//            System.out.println("****************************"+ChoosePrice.toString());
//            arrayAdapter=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,arrayList);
//            ViewP =(ListView)findViewById(R.id.ViewP);
//            ViewP.setAdapter(arrayAdapter);
//            Pay.setOnClickListener(this);
//
//
//
//
//            reference.addChildEventListener(new ChildEventListener() {
//                @Override
//                public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
//                    String value =(String) snapshot.getValue(Product.class).toString();
////                String price = (String) snapshot.child("Price").getValue().toString();
////                String C_Kosher = (String) snapshot.child("category").child("Kosher").getValue().toString();
////                String C_SugerFree = (String) snapshot.child("category").child("SugerFree").getValue().toString();
////                String C_GlutenFree = (String) snapshot.child("category").child("GlutenFree").getValue().toString();
////                String C_Parve = (String) snapshot.child("category").child("Parve").getValue().toString();
////                String C_PenutsFree = (String) snapshot.child("category").child("PenutsFree").getValue().toString();
////                String C_Vegan = (String) snapshot.child("category").child("Vegan").getValue().toString();
//                    //if
//                    arrayList.add(value);
//                    arrayAdapter.notifyDataSetChanged();
//
//                }
//
//                @Override
//                public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
//
//                }
//
//                @Override
//                public void onChildRemoved(@NonNull DataSnapshot snapshot) {
//
//                }
//
//                @Override
//                public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
//
//                }
//
//                @Override
//                public void onCancelled(@NonNull DatabaseError error) {
//
//                }
//            });
//
//            ViewP.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//                @Override
//                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                    item=(String)adapterView.getItemAtPosition(i);//This will give you the same result of viewHolder.LL.setOnClickListener as you are doing
//                    id_of_business_item=id_func(item,"id_of_business=");
//                    num_item=id_func(item,"Num of Product=");
//                    System.out.println("item========"+item);
//                }
//            });
//
//        }
//
//        @Override
//        public void onClick(View v) {
//            if(v==Pay){
//                Intent intent= new Intent(this,CustomerOrderDetails.class);
//                intent.putExtra("Num_Product",num_item);
//                intent.putExtra("id_of_business_item",i
//}
