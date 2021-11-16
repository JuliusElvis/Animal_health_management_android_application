
                            final String finalId = id; //initializes a string named finalId and allocates value of string id to it.
                            db.collection("User").whereEqualTo("email", id).get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                                //db.collection returns the results under User in the database where for a the user and the email given above are a match
                                //add.OnSuccessListener enables creation of a new listener "new OnSuccessListener" that is called when the task is completed successfully.
                                @Override
                                public void onSuccess(QuerySnapshot queryDocumentSnapshots) { //creates a method called onSuccess
                                    //QuerySnapshot contains an object that contains the reults of the query above db.collectin....
                                    User obj = new User(); //initialized a new User named obj
                                    for (QueryDocumentSnapshot doc : queryDocumentSnapshots) //for loop that checks for if the condition in bracket is satisified
                                    //if its satisfied the below statement is executed.
                                        obj = doc.toObject(User.class);
                                        //toObject method converts data in doc and stores it in obj.

                                    db.document("User/" + finalId).update("fcmToken", SharedPref.getInstance(getApplicationContext()).getToken())
                                    //accesses a document in the database under the directory User/ + finalId
                                    //.update returns a new desired state

                                            .addOnCompleteListener(new OnCompleteListener<Void>() {//listener that is called when the task of updating above is complete.
                                                @Override
                                                public void onComplete(@NonNull Task<Void> task) {//method that is initiated after a task is complete to determine if its successful or not

                                                    if (task.isSuccessful()) {//this if statement checks if the task is successful using the isSuccessful method.
                                                        Toast.makeText(SignInActivity.this, "Registered for Notifications Successfully !", Toast.LENGTH_SHORT).show();
                                                        //a toast message is displayed to the user to show them that the registration has succeeded
                                                    } else {//if the above if statement is false, the below instructions are executed.
                                                        Toast.makeText(SignInActivity.this, "Registration for Notifications Failed !\nPlease Sign in Again to Retry", Toast.LENGTH_SHORT).show();
                                                        //a toast message is displayed to the user to show them that the registration has failed
                                                    }
                                                }
                                            });

                                    if (obj.getType() == 0) {//this method is used to return a class object which is then compared using == to se if the returned value is equal to zero.
                                        //if they are equal, the below statements are executed
                                        progressDialog.cancel();//if the above condition in the if statement is satisfied, this statement cancels the progress dialog that was loading.
                                        Toast.makeText(SignInActivity.this, "Signed in !", Toast.LENGTH_SHORT).show();//displays a toast message saying "Signed in !"for a short while to inform the user that they are signed in
                                        startActivity(new Intent(getApplicationContext(), UserHome.class));//Intent is used to move from one activity to another. In this case it is used to move from the current activity to UserHome activity.
                                        finish(); // finish method closes the current activity. This activity is no longer available in the activity stack.

                                    } else {//else is initialized when the above condition is not satisfied
                                        progressDialog.cancel();//if the above condition in the if statement is satisfied, this statement cancels the progress dialog that was loading.
                                        Toast.makeText(SignInActivity.this, "Signed in !", Toast.LENGTH_SHORT).show(); //displays a toast message saying "Signed in !"for a short while to inform the user that they are signed in
                                        startActivity(new Intent(getApplicationContext(), AdminHome.class));//Intent is used to move from one activity to another. In this case it is used to move from the current activity to AdminHome activity.
                                        finish();// finish method closes the current activity. This activity is no longer available in the activity stack.
                                    }
                                }
                            });
                        }
                    });
        }
        @Override //override is used to overwrite the base class method above
        public void failure(TwitterException e) { //a method called failure is created. It is called when there is an error in the application.
            firebaseAuth.signOut(); //Incase of an error in the application, sign out the user.
            System.out.println(e.getMessage()); //System.out.println prints out an error message to show that something unexpected has occurred.
        }
    });
}