package com.example.puzzlescitygame.roomdatabase.entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

@Entity
    public class User {
        @PrimaryKey(autoGenerate = true)
        private int idUser;
        @NonNull
        private String nameUser;
        @NonNull
        private String emailUser;
        @NonNull
        private String birthdateUser;
        @NonNull
        private String genderUser;
        @NonNull
        private String countryUser;

        public User(@NonNull String nameUser, @NonNull String emailUser, @NonNull String birthdateUser, @NonNull String genderUser, @NonNull String countryUser) {
            this.nameUser = nameUser;
            this.emailUser = emailUser;
            this.birthdateUser = birthdateUser;
            this.genderUser = genderUser;
            this.countryUser = countryUser;
        }

        public int getIdUser() {
            return idUser;
        }

        public void setIdUser(int idUser) {
            this.idUser = idUser;
        }

        @NonNull
        public String getNameUser() {
            return nameUser;
        }

        public void setNameUser(@NonNull String nameUser) {
            this.nameUser = nameUser;
        }

        @NonNull
        public String getEmailUser() {
            return emailUser;
        }

        public void setEmailUser(@NonNull String emailUser) {
            this.emailUser = emailUser;
        }

        @NonNull
        public String getBirthdateUser() {
            return birthdateUser;
        }

        public void setBirthdateUser(@NonNull String birthdateUser) {
            this.birthdateUser = birthdateUser;
        }

        @NonNull
        public String getGenderUser() {
            return genderUser;
        }

        public void setGenderUser(@NonNull String genderUser) {
            this.genderUser = genderUser;
        }

        @NonNull
        public String getCountryUser() {
            return countryUser;
        }

        public void setCountryUser(@NonNull String countryUser) {
            this.countryUser = countryUser;
        }
    }