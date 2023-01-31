# SuperPortfolioProject
Sample app this is intended to become into a big portfolio to show my skills!!

## dependecies used:

    //Base
    implementation 'androidx.core:core-ktx:1.7.0'
    implementation 'androidx.appcompat:appcompat:1.5.1'
    implementation 'com.google.android.material:material:1.7.0'
    implementation 'androidx.constraintlayout:constraintlayout:2.1.4'
    implementation 'androidx.lifecycle:lifecycle-runtime-ktx:2.5.1'
    implementation "androidx.lifecycle:lifecycle-viewmodel-ktx:2.5.1"
    implementation "androidx.lifecycle:lifecycle-livedata-ktx:2.5.1"
    implementation 'org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.4'
    implementation "androidx.recyclerview:recyclerview:1.2.1"
    implementation "androidx.cardview:cardview:1.0.0"
    implementation 'androidx.test.ext:junit-ktx:1.1.4'
    testImplementation 'junit:junit:4.13.2'
    testImplementation 'org.robolectric:robolectric:4.4'
    androidTestImplementation 'androidx.test.ext:junit:1.1.4'
    androidTestImplementation 'androidx.test.espresso:espresso-core:3.5.0'
    androidTestImplementation 'androidx.test:runner:1.5.1'
    androidTestImplementation 'androidx.test:rules:1.5.0'
    androidTestImplementation "androidx.arch.core:core-testing:2.1.0"

    //Dagger - Hilt
    implementation "com.google.dagger:hilt-android:2.38.1"
    kapt "com.google.dagger:hilt-android-compiler:2.37"
    implementation "androidx.hilt:hilt-lifecycle-viewmodel:1.0.0-alpha03"
    kapt "androidx.hilt:hilt-compiler:1.0.0"

    // retrofit2
    implementation 'com.squareup.retrofit2:retrofit:2.7.0'
    implementation 'com.squareup.retrofit2:converter-gson:2.7.0'
    implementation 'com.squareup.okhttp3:logging-interceptor:4.2.1'
    implementation 'com.squareup.okio:okio:2.2.2'
    implementation 'com.google.code.gson:gson:2.8.6'

    //Room
    implementation("androidx.room:room-runtime:2.5.0-rc01")
    kapt "androidx.room:room-compiler:2.5.0-rc01"
    implementation "androidx.room:room-ktx:2.5.0-rc01"

    //Glide
    implementation 'com.github.bumptech.glide:glide:4.13.0'
    kapt 'com.github.bumptech.glide:compiler:4.13.0'
    
## Observer4LiveData + ViewmodelProvided to Fragment + instance of RecyclerView Adapter on:
### [RetrofitFragment.kt](https://github.com/gabriel96x1/PortfolioProject/blob/master/app/src/main/java/com/rzs/corroutinesproject/presentation/view/RetrofitFragment.kt)

## Singleton + DI + Retrofit + cache + room instantiation
### [AppModule.kt](https://github.com/gabriel96x1/PortfolioProject/blob/master/app/src/main/java/com/rzs/corroutinesproject/di/AppModule.kt)

## Repository injection + viewModel implementation
### [RetrofitFragmentViewModel.kt](https://github.com/gabriel96x1/PortfolioProject/blob/master/app/src/main/java/com/rzs/corroutinesproject/presentation/viewmodel/RetrofitFragmentViewModel.kt)

## Foreground Service Implementation
### [ForegroundService.kt](https://github.com/gabriel96x1/PortfolioProject/blob/master/app/src/main/java/com/rzs/corroutinesproject/domain/services/ForegroundService.kt)
### LaunchingForeground Service: [AndroidServicesFragment.kt](https://github.com/gabriel96x1/PortfolioProject/blob/master/app/src/main/java/com/rzs/corroutinesproject/presentation/view/AndroidServicesFragment.kt)

## BroadcastReceiver Implementation (Stop Foreground Service)
### [StopForegroundServiceReceiver.kt](https://github.com/gabriel96x1/PortfolioProject/blob/master/app/src/main/java/com/rzs/corroutinesproject/domain/receivers/StopForegroundServiceReceiver.kt)

## InfiniteList RecyclerView
### [Fragment](https://github.com/gabriel96x1/PortfolioProject/blob/master/app/src/main/java/com/rzs/corroutinesproject/presentation/view/RecyclerViewPaginationFragment.kt)
### [ViewModel](https://github.com/gabriel96x1/PortfolioProject/blob/master/app/src/main/java/com/rzs/corroutinesproject/presentation/viewmodel/RecyclerViewPaginationFragmentViewModel.kt)
### [Adapter](https://github.com/gabriel96x1/PortfolioProject/blob/master/app/src/main/java/com/rzs/corroutinesproject/presentation/recyclerview/paginationrecyclerview/PaginationViewAdapter.kt)

# Code Snippets
SharedPreferences usage:

            val sharedPref = getSharedPreferences("myPref", MODE_PRIVATE)
            val editor = sharedPref.edit()
            
            val userName = edtUsername.text.toString()
            val email = edtEmail.text.toString()

                editor.apply {
                    putString("user_name",userName)
                    putString("email",email)
                    apply()
                }
                
                val userName = sharedPref.getString("user_name",null)
                val email = sharedPref.getString("email",null)
