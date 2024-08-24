package com.example.pethelp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.pethelp.databinding.ActivitySignupBinding;

public class signupActivity extends AppCompatActivity {

    ActivitySignupBinding binding;
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignupBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        databaseHelper = new DatabaseHelper(this);

        binding.signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = binding.signupEmail.getText().toString();
                String password = binding.signupPassword.getText().toString();
                String confirmPassword = binding.signupConfirm.getText().toString();
                boolean isTermsChecked = binding.agreeTermsCheckbox.isChecked();

                if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    Toast.makeText(signupActivity.this, "Please enter a valid email address", Toast.LENGTH_SHORT).show();
                }

                else if (password.isEmpty() || confirmPassword.isEmpty() || !isTermsChecked) {
                    Toast.makeText(signupActivity.this, "Fields cannot be blank and Terms must be agreed", Toast.LENGTH_SHORT).show();
                } else if (!isValidLength(password)) {
                    Toast.makeText(signupActivity.this, "Password must be at least 8 characters long", Toast.LENGTH_LONG).show();
                } else if (!isValidUppercase(password)) {
                    Toast.makeText(signupActivity.this, "Password must has some Uppercase letters", Toast.LENGTH_LONG).show();
                } else if (!isValidLowercase(password)) {
                    Toast.makeText(signupActivity.this, "Password must has some Lowercase letters", Toast.LENGTH_LONG).show();
                } else if (!isValidDigit(password)) {
                    Toast.makeText(signupActivity.this, "Password must has some Digits", Toast.LENGTH_LONG).show();
                } else if (!isValidSpecialCharacter(password)) {
                    Toast.makeText(signupActivity.this, "Password must has some special characters", Toast.LENGTH_LONG).show();
                }


                else if (!password.equals(confirmPassword)) {
                    Toast.makeText(signupActivity.this, "Passwords do not match", Toast.LENGTH_SHORT).show();
                } else {
                    Boolean checkUserEmail = databaseHelper.checkEmail(email);

                    if (!checkUserEmail) {
                        Boolean insert = databaseHelper.insertData(email, password);

                        if (insert) {
                            Intent intent = new Intent(getApplicationContext(), pet_register.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(signupActivity.this, "Sign Up Failed", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(signupActivity.this, "You already exist, Please Login", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            private boolean isValidLength(String password) {
                if (password.length() < 8) {
                    return false;
                }
                return true;
            }

            private boolean isValidUppercase(String password) {
                if (!password.matches(".*[A-Z].*")) {
                    return false;
                }
                return true;
            }

            private boolean isValidLowercase(String password) {
                if (!password.matches(".*[a-z].*")) {
                    return false;
                }
                return true;
            }

            private boolean isValidDigit(String password) {
                if (!password.matches(".*\\d.*")) {
                    return false;
                }
                return true;
            }

            private boolean isValidSpecialCharacter(String password) {
                if (!password.matches(".*[@#$%^&+=].*")) {
                    return false;
                }
                return true;
            }

        });

        binding.loginRedirectText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            }
        });

        binding.termsAndConditionsText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Condition.class);
                startActivity(intent);
            }
        });


    }
}
