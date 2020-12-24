//import androidx.annotation.NonNull;add.setOnClickListener(new View.OnClickListener() {
//@Override
//public void onClick(View v) {
//        rootNode=FirebaseDatabase.getInstance();
//        rootReference=rootNode.getReference();
//        info = new Info();
//        newInfo= addInfo.getText().toString();
//        System.out.println("newInfo========="+ newInfo);
//
//        info.information=newInfo;
//        info.idManager=managerId;
//        System.out.println("(((((((((((( info.information"+ info.information);
//
//        boolean flag=true;
//
//        rootReference=rootNode.getReference("ManagerInformation");
//        rootReference.addListenerForSingleValueEvent(new ValueEventListener() {
//@Override
//public void onDataChange(@NonNull DataSnapshot snapshot) {
//        String id=info.information;
//        rootReference.child(id).setValue(info); //add to firebase/
//
//        Intent intent = new Intent(AddInformation.this, ManagerOptionsActivity.class);
//        startActivity(intent);
//        }
//
//@Override
//public void onCancelled(@NonNull DatabaseError error) {
//
//        }
//        });
//        }
//        });