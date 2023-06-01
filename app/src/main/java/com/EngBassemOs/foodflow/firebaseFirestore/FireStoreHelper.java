package com.EngBassemOs.foodflow.firebaseFirestore;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

import com.EngBassemOs.foodflow.auth.login.controller.LoginPresenter;
import com.EngBassemOs.foodflow.auth.login.controller.LoginPresenterInterface;
import com.EngBassemOs.foodflow.db.MealDetailDao;
import com.EngBassemOs.foodflow.home.presenter.HomePresenter;
import com.EngBassemOs.foodflow.home.presenter.HomePresenterInterface;
import com.EngBassemOs.foodflow.model.DetailMeal;
import com.EngBassemOs.foodflow.model.PlanMeal;
import com.EngBassemOs.foodflow.network.AppClient;
import com.EngBassemOs.foodflow.profile.presenter.ProfilePresenter;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executor;

public class FireStoreHelper {
    private FirebaseFirestore db ;
    FirebaseAuth auth ;
    String userId ;
    LoginPresenterInterface loginPresenter;
    ProfilePresenter profilePresenter;

    public FireStoreHelper() {
        db=FirebaseFirestore.getInstance();
        auth=FirebaseAuth.getInstance();
        if (auth.getCurrentUser()!=null){
            try{
                userId=auth.getCurrentUser().getUid();
            }catch (Exception e){
                System.out.println(e.toString());
            };
        }

    }

    public FireStoreHelper(LoginPresenterInterface loginPresenter) {
        db=FirebaseFirestore.getInstance();
        auth=FirebaseAuth.getInstance();
        userId=auth.getCurrentUser().getUid();
        this.loginPresenter=loginPresenter;

    }

    public void postPlanMeals(LiveData<List<PlanMeal>> planMeals,String mainUser){
        planMeals.observeForever(meals -> {
            for (PlanMeal meal : meals) {
                Map<String, Object> docData = new HashMap<>();
                docData.put("idMeal", meal.getIdMeal());
                docData.put("strMeal", meal.getStrMeal());
                docData.put("strDrinkAlternate", meal.getStrDrinkAlternate());
                docData.put("strCategory", meal.getStrCategory());
                docData.put("strArea", meal.getStrArea());
                docData.put("strInstructions", meal.getStrInstructions());
                docData.put("strMealThumb", meal.getStrMealThumb());
                docData.put("strTags", meal.getStrTags());
                docData.put("strYoutube", meal.getStrYoutube());
                docData.put("ing1", meal.getIng1());
                docData.put("ing2", meal.getIng2());
                docData.put("ing3", meal.getIng3());
                docData.put("ing4", meal.getIng4());
                docData.put("ing5", meal.getIng5());
                docData.put("ing6", meal.getIng6());
                docData.put("ing7", meal.getIng7());
                docData.put("ing8", meal.getIng8());
                docData.put("ing9", meal.getIng9());
                docData.put("ing10", meal.getIng10());
                docData.put("ing11", meal.getIng11());
                docData.put("ing12", meal.getIng12());
                docData.put("ing13", meal.getIng13());
                docData.put("ing14", meal.getIng14());
                docData.put("ing15", meal.getIng15());
                docData.put("ing16", meal.getIng16());
                docData.put("ing17", meal.getIng17());
                docData.put("ing18", meal.getIng18());
                docData.put("ing19", meal.getIng19());
                docData.put("ing20", meal.getIng20());
                docData.put("meas1", meal.getMeas1());
                docData.put("meas2", meal.getMeas2());
                docData.put("meas3", meal.getMeas3());
                docData.put("meas4", meal.getMeas4());
                docData.put("meas5", meal.getMeas5());
                docData.put("meas6", meal.getMeas6());
                docData.put("meas7", meal.getMeas7());
                docData.put("meas8", meal.getMeas8());
                docData.put("meas9", meal.getMeas9());
                docData.put("meas10", meal.getMeas10());
                docData.put("meas11", meal.getMeas11());
                docData.put("meas12", meal.getMeas12());
                docData.put("meas13", meal.getMeas13());
                docData.put("meas14", meal.getMeas14());
                docData.put("meas15", meal.getMeas15());
                docData.put("meas16", meal.getMeas16());
                docData.put("meas17", meal.getMeas17());
                docData.put("meas18", meal.getMeas18());
                docData.put("meas19", meal.getMeas19());
                docData.put("meas20", meal.getMeas20());
                docData.put("day",meal.getDay());

                db.collection(mainUser).document("type").collection("planMeal").document(meal.getIdMeal())
                        .set(docData).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                System.out.println("successssssssss");
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                System.out.println("faillll becase "+ e.toString());
                            }
                        });

            }

        });

    }


    public void postFavouriteMeals(LiveData<List<DetailMeal>> favouriteMeals,String mainUserId) {
        favouriteMeals.observeForever(meals -> {
            for (DetailMeal meal : meals) {
                Map<String, Object> docData = new HashMap<>();
                docData.put("idMeal", meal.getIdMeal());
                docData.put("strMeal", meal.getStrMeal());
                docData.put("strDrinkAlternate", meal.getStrDrinkAlternate());
                docData.put("strCategory", meal.getStrCategory());
                docData.put("strArea", meal.getStrArea());
                docData.put("strInstructions", meal.getStrInstructions());
                docData.put("strMealThumb", meal.getStrMealThumb());
                docData.put("strTags", meal.getStrTags());
                docData.put("strYoutube", meal.getStrYoutube());
                docData.put("ing1", meal.getIng1());
                docData.put("ing2", meal.getIng2());
                docData.put("ing3", meal.getIng3());
                docData.put("ing4", meal.getIng4());
                docData.put("ing5", meal.getIng5());
                docData.put("ing6", meal.getIng6());
                docData.put("ing7", meal.getIng7());
                docData.put("ing8", meal.getIng8());
                docData.put("ing9", meal.getIng9());
                docData.put("ing10", meal.getIng10());
                docData.put("ing11", meal.getIng11());
                docData.put("ing12", meal.getIng12());
                docData.put("ing13", meal.getIng13());
                docData.put("ing14", meal.getIng14());
                docData.put("ing15", meal.getIng15());
                docData.put("ing16", meal.getIng16());
                docData.put("ing17", meal.getIng17());
                docData.put("ing18", meal.getIng18());
                docData.put("ing19", meal.getIng19());
                docData.put("ing20", meal.getIng20());
                docData.put("meas1", meal.getMeas1());
                docData.put("meas2", meal.getMeas2());
                docData.put("meas3", meal.getMeas3());
                docData.put("meas4", meal.getMeas4());
                docData.put("meas5", meal.getMeas5());
                docData.put("meas6", meal.getMeas6());
                docData.put("meas7", meal.getMeas7());
                docData.put("meas8", meal.getMeas8());
                docData.put("meas9", meal.getMeas9());
                docData.put("meas10", meal.getMeas10());
                docData.put("meas11", meal.getMeas11());
                docData.put("meas12", meal.getMeas12());
                docData.put("meas13", meal.getMeas13());
                docData.put("meas14", meal.getMeas14());
                docData.put("meas15", meal.getMeas15());
                docData.put("meas16", meal.getMeas16());
                docData.put("meas17", meal.getMeas17());
                docData.put("meas18", meal.getMeas18());
                docData.put("meas19", meal.getMeas19());
                docData.put("meas20", meal.getMeas20());

                db.collection(mainUserId).document("type").collection("favMeals").document(meal.getIdMeal())
                        .set(docData).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                System.out.println("successssssssss");
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                System.out.println("faillll becase "+ e.toString());
                            }
                        });

            }


        });
    }
    public void getFavouriteMeals(){
        List<DetailMeal> detailMeals = new ArrayList<>();

        db.collection(userId).document("type").collection("favMeals").get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                DetailMeal detailMeal = new DetailMeal();
                                detailMeal.setIdMeal(document.getString("idMeal"));
                                detailMeal.setStrMeal(document.getString("strMeal"));
                                detailMeal.setStrCategory(document.getString("strCategory"));
                                detailMeal.setStrArea(document.getString("strArea"));
                                detailMeal.setStrInstructions(document.getString("strInstructions"));
                                detailMeal.setStrMealThumb(document.getString("strMealThumb"));
                                detailMeal.setStrTags(document.getString("strTags"));
                                detailMeal.setStrYoutube(document.getString("strYoutube"));
                                detailMeal.setStrDrinkAlternate(document.getString("strDrinkAlternate"));
                                detailMeal.setIng1(document.getString("ing1"));
                                detailMeal.setIng2(document.getString("ing2"));
                                detailMeal.setIng3(document.getString("ing3"));
                                detailMeal.setIng4(document.getString("ing4"));
                                detailMeal.setIng5(document.getString("ing5"));
                                detailMeal.setIng6(document.getString("ing6"));
                                detailMeal.setIng7(document.getString("ing7"));
                                detailMeal.setIng8(document.getString("ing8"));
                                detailMeal.setIng9(document.getString("ing9"));
                                detailMeal.setIng10(document.getString("ing10"));
                                detailMeal.setIng11(document.getString("ing11"));
                                detailMeal.setIng12(document.getString("ing12"));
                                detailMeal.setIng13(document.getString("ing13"));
                                detailMeal.setIng14(document.getString("ing14"));
                                detailMeal.setIng15(document.getString("ing15"));
                                detailMeal.setIng16(document.getString("ing16"));
                                detailMeal.setIng17(document.getString("ing17"));
                                detailMeal.setIng18(document.getString("ing18"));
                                detailMeal.setIng19(document.getString("ing19"));
                                detailMeal.setIng20(document.getString("ing120"));
                               //this for ingreidient----------------------------------------
                                detailMeal.setMeas1(document.getString("meas1"));
                                detailMeal.setMeas2(document.getString("meas2"));
                                detailMeal.setMeas3(document.getString("meas3"));
                                detailMeal.setMeas4(document.getString("meas4"));
                                detailMeal.setMeas5(document.getString("meas5"));
                                detailMeal.setMeas6(document.getString("meas6"));
                                detailMeal.setMeas7(document.getString("meas7"));
                                detailMeal.setMeas8(document.getString("meas8"));
                                detailMeal.setMeas9(document.getString("meas9"));
                                detailMeal.setMeas10(document.getString("meas10"));
                                detailMeal.setMeas11(document.getString("meas11"));
                                detailMeal.setMeas12(document.getString("meas12"));
                                detailMeal.setMeas13(document.getString("meas13"));
                                detailMeal.setMeas14(document.getString("meas14"));
                                detailMeal.setMeas15(document.getString("meas15"));
                                detailMeal.setMeas16(document.getString("meas16"));
                                detailMeal.setMeas17(document.getString("meas17"));
                                detailMeal.setMeas18(document.getString("meas18"));
                                detailMeal.setMeas19(document.getString("meas19"));
                                detailMeal.setMeas20(document.getString("meas20"));
                                //this for measure---------------------------------------

                                detailMeals.add(detailMeal);
                                loginPresenter.fillTheFavTable(detailMeal);
                                System.out.println(detailMeal.getStrMeal());

                            }
                            getPlanMeals();

                        } else {
                            System.out.println("failllllll");
                        }
                    }
                });


    }
    public void getPlanMeals(){
        List<PlanMeal> planbaseMeals = new ArrayList<>();

        db.collection(userId).document("type").collection("planMeal").get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                PlanMeal planMeals = new PlanMeal();
                                planMeals.setIdMeal(document.getString("idMeal"));
                                planMeals.setStrMeal(document.getString("strMeal"));
                                planMeals.setStrCategory(document.getString("strCategory"));
                                planMeals.setStrArea(document.getString("strArea"));
                                planMeals.setStrInstructions(document.getString("strInstructions"));
                                planMeals.setStrMealThumb(document.getString("strMealThumb"));
                                planMeals.setStrTags(document.getString("strTags"));
                                planMeals.setStrYoutube(document.getString("strYoutube"));
                                planMeals.setStrDrinkAlternate(document.getString("strDrinkAlternate"));
                                planMeals.setIng1(document.getString("ing1"));
                                planMeals.setIng2(document.getString("ing2"));
                                planMeals.setIng3(document.getString("ing3"));
                                planMeals.setIng4(document.getString("ing4"));
                                planMeals.setIng5(document.getString("ing5"));
                                planMeals.setIng6(document.getString("ing6"));
                                planMeals.setIng7(document.getString("ing7"));
                                planMeals.setIng8(document.getString("ing8"));
                                planMeals.setIng9(document.getString("ing9"));
                                planMeals.setIng10(document.getString("ing10"));
                                planMeals.setIng11(document.getString("ing11"));
                                planMeals.setIng12(document.getString("ing12"));
                                planMeals.setIng13(document.getString("ing13"));
                                planMeals.setIng14(document.getString("ing14"));
                                planMeals.setIng15(document.getString("ing15"));
                                planMeals.setIng16(document.getString("ing16"));
                                planMeals.setIng17(document.getString("ing17"));
                                planMeals.setIng18(document.getString("ing18"));
                                planMeals.setIng19(document.getString("ing19"));
                                planMeals.setIng20(document.getString("ing120"));
                                //this for ingreidient----------------------------------------
                                planMeals.setMeas1(document.getString("meas1"));
                                planMeals.setMeas2(document.getString("meas2"));
                                planMeals.setMeas3(document.getString("meas3"));
                                planMeals.setMeas4(document.getString("meas4"));
                                planMeals.setMeas5(document.getString("meas5"));
                                planMeals.setMeas6(document.getString("meas6"));
                                planMeals.setMeas7(document.getString("meas7"));
                                planMeals.setMeas8(document.getString("meas8"));
                                planMeals.setMeas9(document.getString("meas9"));
                                planMeals.setMeas10(document.getString("meas10"));
                                planMeals.setMeas11(document.getString("meas11"));
                                planMeals.setMeas12(document.getString("meas12"));
                                planMeals.setMeas13(document.getString("meas13"));
                                planMeals.setMeas14(document.getString("meas14"));
                                planMeals.setMeas15(document.getString("meas15"));
                                planMeals.setMeas16(document.getString("meas16"));
                                planMeals.setMeas17(document.getString("meas17"));
                                planMeals.setMeas18(document.getString("meas18"));
                                planMeals.setMeas19(document.getString("meas19"));
                                planMeals.setMeas20(document.getString("meas20"));
                                //this for measure---------------------------------------
                                planMeals.setDay(document.getString("day"));
                                planbaseMeals.add(planMeals);
                                loginPresenter.fillThePlanMealTable(planMeals);


                            }
                            clearFirebase();


                        } else {
                            System.out.println("failllllll");
                        }
                    }
                });


    }
    public void clearFirebase(){
        db.collection(userId).document("type")
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        DocumentSnapshot documentSnapshot = task.getResult();
                        if (documentSnapshot != null && documentSnapshot.exists()) {
                            // Get a reference to the 'favMeals' collection
                            CollectionReference favMealsCollection = documentSnapshot.getReference().collection("favMeals");
                            // Get a reference to the 'planMeals' collection
                            CollectionReference planMealsCollection = documentSnapshot.getReference().collection("planMeals");

                            // Delete all documents within 'favMeals' collection
                            deleteCollection(favMealsCollection);
                            // Delete all documents within 'planMeals' collection
                            deleteCollection(planMealsCollection);
                        }
                    }
                });


    }

    private void deleteCollection(CollectionReference collection) {
        collection.get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            document.getReference().delete();
                        }
                    }
                });
    }
    public void addUserData(String userId, String email, String displayName, String photoUrl) {
        // Create a new document with the user data
        Map<String, Object> userData = new HashMap<>();
        userData.put("userId", userId);
        userData.put("email", email);
        userData.put("displayName", displayName);
        userData.put("photoUrl", photoUrl);

        // Add the document to the "users" collection
        db.collection("users")
                .document(userId)
                .set(userData)
                .addOnSuccessListener(aVoid -> {
                    // Document added successfully
                    Log.d("TAG", "User data added to Firestore");
                })
                .addOnFailureListener(e -> {
                    // Error occurred while adding the document
                    Log.e("TAG", "Error adding user data to Firestore", e);
                });
    }








}
