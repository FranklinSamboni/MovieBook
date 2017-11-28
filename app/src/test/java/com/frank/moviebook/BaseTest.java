package com.frank.moviebook;

import org.junit.Before;
import org.mockito.MockitoAnnotations;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.Shadows;
import org.robolectric.shadows.ShadowApplication;

/**
 * Created by FRANK on 27/11/2017.
 */

public abstract class BaseTest {

    @Before
    public void setUp() throws Exception{
        MockitoAnnotations.initMocks(this);

        if(RuntimeEnvironment.application != null){
            ShadowApplication shadowApplication = Shadows.shadowOf(RuntimeEnvironment.application);
            shadowApplication.grantPermissions("android.permission.INTERNET");
        }
    }


}
