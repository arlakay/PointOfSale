package com.ingenico.PointOfSale;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;

import com.ingenico.PointOfSale.Controller.SessionManager;
import com.ingenico.PointOfSale.Controller.UserInterface;
import com.ingenico.PointOfSale.ModelRealm.Category;
import com.ingenico.PointOfSale.ModelRealm.DiscountMaster;
import com.ingenico.PointOfSale.ModelRealm.Drawer;
import com.ingenico.PointOfSale.ModelRealm.Item;
import com.ingenico.PointOfSale.ModelRealm.ItemModifierGroup;
import com.ingenico.PointOfSale.ModelRealm.ItemVariant;
import com.ingenico.PointOfSale.ModelRealm.Login;
import com.ingenico.PointOfSale.ModelRealm.Merchant;
import com.ingenico.PointOfSale.ModelRealm.ModifierDetail;
import com.ingenico.PointOfSale.ModelRealm.ModifierGroup;
import com.ingenico.PointOfSale.ModelRealm.Outlet;
import com.ingenico.PointOfSale.ModelRealm.SaveOrder;
import com.ingenico.PointOfSale.ModelRealm.Service;
import com.ingenico.PointOfSale.ModelRealm.Tax;
import com.ingenico.PointOfSale.ModelRealm.Variant;
import com.ingenico.PointOfSale.ZoneActivity.ChoiceOutlet;
import com.ingenico.PointOfSale.ZoneActivity.LoginActivity;
import com.ingenico.PointOfSale.ZoneActivity.StaffDashboardActivity;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class SplashActivity extends CommonActivity {

    /** Duration of wait **/
    private final int SPLASH_DISPLAY_LENGTH = 3000;
    /**
     * The thread to process splash screen events
     */
    private Thread mSplashThread;
    /**
     * The thread to manage user interface
     */
    UserInterface userInterface;

    /**
     * Realm is a database for every data in apps
     */
    public Realm realm;
    public static RealmConfiguration realmConfig;

    private SessionManager sessionManager;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.splash);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        /**
         * Create Realm configuration if it doesn't exist.
         */
        realmConfig = new RealmConfiguration.Builder(this).build();
        /**
         * Open the default Realm for the UI thread.
         */
        this.realm = Realm.getInstance(realmConfig);

        sessionManager = new SessionManager(this);
        //initService();

        if (sessionManager.getKeyAccessCode().equals("null data")){cleanUp();}

        // Clean up from previous run
        // The thread to wait for splash screen events
        mSplashThread =  new Thread(){
            @Override
            public void run(){
                try {
                    synchronized(this){
                        // Wait given period of time or exit on touch
                    	this.wait(SPLASH_DISPLAY_LENGTH);
                    }
                }
                catch(InterruptedException ex){                    
                }

                if (!sessionManager.getKeyAccessCode().equals("null data")){
                    if (sessionManager.isCashierLoggedIn()){
                        startActivity(new Intent(SplashActivity.this, CashRegisterActivity.class));
                        finish();
                    }else if (sessionManager.isOutletLoggedIn()){
                        startActivity(new Intent(SplashActivity.this, StaffDashboardActivity.class));
                        finish();
                    } else if (sessionManager.isMerchantLoggedIn()) {
                        startActivity(new Intent(SplashActivity.this, ChoiceOutlet.class));
                        finish();
                    }
                }else {
                    startActivity(new Intent(SplashActivity.this,LoginActivity.class));
                    finish();
                }
            }
        };
        
        mSplashThread.start();      
    }

	@Override
	protected void onDestroy() {
		releaseService();
		super.onDestroy();
	}
	
	/**
     * Processes splash screen touch events
     */
    @Override
    public boolean onTouchEvent(MotionEvent evt)
    {
        if(evt.getAction() == MotionEvent.ACTION_DOWN)
        {
            synchronized(mSplashThread){
            	mSplashThread.notifyAll();
            }
        }
        return true;
    }

	@Override
	public void onBarCodeReceived(String barCodeValue, int symbology) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onBarCodeClosed() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStateChanged(String state) {
		synchronized(mSplashThread){
            mSplashThread.notifyAll();
        }
	}

	@Override
    public void onPclServiceConnected() {
		Log.d(TAG, "onPclServiceConnected");
		mPclService.addDynamicBridgeLocal(6000, 0);
	}

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        userInterface = new UserInterface(this);
        if (hasFocus){
            userInterface.hideSystemUIFull();
        }
    }

    public void cleanUp() {

//        sessionManager.clearSessionManager();

        // Delete all persons
        realm.beginTransaction();

        realm.allObjects(Category.class).deleteAllFromRealm();
        realm.allObjects(DiscountMaster.class).deleteAllFromRealm();
        realm.allObjects(Drawer.class).deleteAllFromRealm();
        realm.allObjects(Item.class).deleteAllFromRealm();
        realm.allObjects(ItemModifierGroup.class).deleteAllFromRealm();
        realm.allObjects(ItemVariant.class).deleteAllFromRealm();
        realm.allObjects(Login.class).deleteAllFromRealm();
        realm.allObjects(Merchant.class).deleteAllFromRealm();
        realm.allObjects(ModifierDetail.class).deleteAllFromRealm();
        realm.allObjects(ModifierGroup.class).deleteAllFromRealm();
        realm.allObjects(Outlet.class).deleteAllFromRealm();
        realm.allObjects(SaveOrder.class).deleteAllFromRealm();
        realm.allObjects(Service.class).deleteAllFromRealm();
        realm.allObjects(Tax.class).deleteAllFromRealm();
//        realm.allObjects(TransactionDetail.class).deleteAllFromRealm();
//        realm.allObjects(TransactionDetailDiscount.class).deleteAllFromRealm();
//        realm.allObjects(TransactionDetailModifier.class).deleteAllFromRealm();
//        realm.allObjects(TransactionMaster.class).deleteAllFromRealm();
        realm.allObjects(Variant.class).deleteAllFromRealm();

        realm.commitTransaction();
    }
}