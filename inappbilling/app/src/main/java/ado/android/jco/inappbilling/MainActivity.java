package ado.android.jco.inappbilling;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;



import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import util.IabHelper;
import util.IabResult;
import util.Inventory;
import util.Purchase;

    public class MainActivity extends AppCompatActivity {


        private static final String TAG = "InAppBilling";
        IabHelper mHelper;
        static final String FIFTY_KEYS = "FIFTY_KEYS";
        static final String HUNDRED_KEYS = "HUNDRED_KEYS";
        static final String TWO_HUNDRED_KEYS = "TWO_HUNDRED_KEYS";
        static final String THREE_HUNDRED_KEYS = "THREE_HUNDRED_KEYS";
        static final String FOUR_HUNDRED_KEYS = "FOUR_HUNDRED_KEYS";

        private Button clickButton;
        private Button buyButton;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            buyButton = (Button)findViewById(R.id.buyButton);
            clickButton = (Button)findViewById(R.id.clickButton);
            clickButton.setEnabled(false);

            clickButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    clickButton.setEnabled(false);
                    buyButton.setEnabled(true);
                }
            });

            String base64EncodedPublicKey ="MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAgoTlaBWv5Jjt6AWdEJgeKbp0mNvehyJXEtyCSreGWyEtFhDGH0m6P2Xr0ioDTmufdFssjdidUGEnPuBvsyWMWiHkWEXRiefKpRom7gSqFcnRrPlmINs2FhETCHkkaZqZ03iPAP9x790AcMBQP+kbk0lkOn27NVhxa+rRd1UAMSpPCK896j64Oxbhlw2zaeJgnn+/eGLvyYf/Ffr+DCtwriM115SmVlntvQhdZi48gC9Fz+NEXN2Mvk3hTkbivH+gHqcngW+lB6qoHk4yvFDq4VJc1y9T+lXvGIKYbVgJ8JFPFPj3u6+C8WXJQ8w5zhGwnHBw3VQ7wDd5dixyDP38ZwIDAQAB";
                    mHelper = new IabHelper(this, base64EncodedPublicKey);
            mHelper.startSetup(new IabHelper.OnIabSetupFinishedListener() {
                @Override
                public void onIabSetupFinished(IabResult result) {
                    if (!result.isSuccess()) {

                        System.out.println("FAILED");
                    } else {
                        System.out.println("OK");

                    }
                }
            });

        }

        public void buyClick(View view){
            try {
                mHelper.launchPurchaseFlow(this, FIFTY_KEYS, 10001, mPurchaseFinishedLater, "mypurchasetoken");
            }
            catch (Exception ex){

            }
        }

        @Override
        protected void onActivityResult(int requestCode, int resultCode, Intent data) {

            if (!mHelper.handleActivityResult(requestCode, resultCode, data)) {
                super.onActivityResult(requestCode, resultCode, data);
            }
        }

        IabHelper.OnIabPurchaseFinishedListener mPurchaseFinishedLater = new IabHelper.OnIabPurchaseFinishedListener() {
            @Override
            public void onIabPurchaseFinished(IabResult result, Purchase info) {
                if(result.isFailure()){
                    return;
                }
                else if(info.getSku().equals(FIFTY_KEYS)){
                    consumeItem();
                    buyButton.setEnabled(true);
                }
            }
        };

        public void consumeItem(){
            try {
                mHelper.queryInventoryAsync(mReceivedInventoryListener);

            }
            catch(Exception ex){

            }
        }

        IabHelper.QueryInventoryFinishedListener mReceivedInventoryListener = new IabHelper.QueryInventoryFinishedListener() {
            @Override
            public void onQueryInventoryFinished(IabResult result, Inventory inv) {
                if(result.isFailure()){

                }
                else {
                    try {
                        mHelper.consumeAsync(inv.getPurchase(FIFTY_KEYS), mConsumeFinishedListener);
                    }
                    catch (Exception ex){

                    }
                }
            }

        };

        IabHelper.OnConsumeFinishedListener mConsumeFinishedListener = new IabHelper.OnConsumeFinishedListener() {
            @Override
            public void onConsumeFinished(Purchase purchase, IabResult result) {
                if(result.isSuccess()){
                    clickButton.setEnabled(true);
                }
                else{

                }
            }
        };


        @Override
        protected void onDestroy() {
            super.onDestroy();
            try {
                if (mHelper != null)
                    mHelper.dispose();
                mHelper = null;
            }
            catch (Exception ex){

            }
        }
    }

