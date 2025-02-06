package com.one.groceryapp.ui.fragment;

import static android.app.Activity.RESULT_CANCELED;
import static android.app.Activity.RESULT_OK;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.firebase.auth.FirebaseAuth;
import com.one.groceryapp.R;
import com.one.groceryapp.databinding.FragmentProfileBinding;
import com.one.groceryapp.roomdb.AppDatabase;
import com.one.groceryapp.roomdb.UserDao;
import com.one.groceryapp.ui.activity.AboutMeActivity;
import com.one.groceryapp.ui.activity.LoginActivity;
import com.one.groceryapp.ui.activity.MyAddressActivity;
import com.one.groceryapp.ui.activity.MyCardsActivity;
import com.one.groceryapp.ui.activity.MyOrderActivity;
import com.one.groceryapp.ui.activity.NotificationActivity;
import com.one.groceryapp.ui.activity.TransactionActivity;

import java.io.IOException;


public class ProfileFragment extends Fragment {

    private static final int CAMERA_PIC_REQUEST = 1;
    private static final int RESULT_GALLERY = 2;
    FragmentProfileBinding binding;
    FirebaseAuth mAuth;
    AppDatabase appDatabase;
    UserDao userDao;

    RelativeLayout bottomsheet;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentProfileBinding.inflate(inflater, container, false);
        mAuth = FirebaseAuth.getInstance();

        binding.signoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent i = new Intent(getContext(), LoginActivity.class);
                startActivity(i);
            }
        });

        String email = mAuth.getCurrentUser().getEmail();
        binding.email.setText(email);

        binding.camera.setOnClickListener(v -> {
            final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(getContext());
            bottomSheetDialog.setContentView(R.layout.bottom_sheet);

            bottomSheetDialog.findViewById(R.id.camera_layout).setOnClickListener(v1 -> {
                if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.CAMERA)
                        == PackageManager.PERMISSION_DENIED) {
                    ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CAMERA}, CAMERA_PIC_REQUEST);
                } else {
                    Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
                    startActivityForResult(intent, CAMERA_PIC_REQUEST);
                    bottomSheetDialog.dismiss();
                }
            });

            bottomSheetDialog.findViewById(R.id.gallery_layout).setOnClickListener(v1 -> {
                if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.READ_EXTERNAL_STORAGE)
                        == PackageManager.PERMISSION_DENIED) {
                    ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, RESULT_GALLERY);
                } else {
                    Intent galleryIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(galleryIntent, RESULT_GALLERY);
                    bottomSheetDialog.dismiss();
                }
            });
            bottomSheetDialog.show();
        });

        String name = splitDomainName(email, "@");
        binding.name.setText(name);

        binding.aboutme.setOnClickListener(v -> {
            Intent i = new Intent(getContext(), AboutMeActivity.class);
            i.putExtra("name", name);
            i.putExtra("email", email);
            startActivity(i);
        });

        binding.creditcard.setOnClickListener(v -> {
            Intent i = new Intent(getContext(), MyCardsActivity.class);
            startActivity(i);
        });

        binding.notificationRL.setOnClickListener(v -> {
            Intent i = new Intent(getContext(), NotificationActivity.class);
            startActivity(i);
        });

        binding.transansaction.setOnClickListener(v -> {
            Intent i = new Intent(getContext(), TransactionActivity.class);
            startActivity(i);
        });

        binding.myorder.setOnClickListener(v -> {
            Intent i = new Intent(getContext(), MyOrderActivity.class);
            startActivity(i);
        });

        binding.myaddress.setOnClickListener(v -> {
            Intent i = new Intent(getContext(), MyAddressActivity.class);
            startActivity(i);
        });

        binding.myfavorite.setOnClickListener(v -> {
            swapFragment();
        });

        return binding.getRoot();
    }

    private void swapFragment() {
        LikeFragment newGamefragment = new LikeFragment();
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, newGamefragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        appDatabase = AppDatabase.getInstance(getContext());
        userDao = appDatabase.userDao();


        if (resultCode == CAMERA_PIC_REQUEST) {
            if (requestCode != RESULT_CANCELED) {
                Bitmap photo = (Bitmap) data.getExtras().get("data");
                binding.profileImage.setImageBitmap(photo);
            }
        } else if (requestCode == RESULT_GALLERY) {
            if (resultCode == RESULT_OK) {
                Uri imageUri = data.getData();
                binding.profileImage.setImageURI(imageUri);
            }
        }
    }

    public static String splitDomainName(String str, String delimeter) {
        String finalOutput = "";
        String arrayOfStr[] = str.split(delimeter);
        if (arrayOfStr.length == 2) {
            finalOutput = arrayOfStr[0];
        }
        return finalOutput;
    }
}



