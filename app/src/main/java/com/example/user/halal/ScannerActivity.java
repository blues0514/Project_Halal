package com.example.user.halal;

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class ScannerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    protected void onResume() {
        super.onResume();
        IntentIntegrator integrator = new IntentIntegrator(this);
        integrator.setCaptureActivity(CustomScannerActivity.class);
        integrator.initiateScan();
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        Log.d("onActivityResult", "onActivityResult: .");
        if (resultCode == Activity.RESULT_OK) {
            IntentResult scanResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
            String re = scanResult.getContents();
            Log.d("onActivityResult", "onActivityResult: ." + re);
            //Toast.makeText(this, re, Toast.LENGTH_LONG).show();
            clipBarcode(re);
            //onBackPressed();
        }
    }

    public void switchFlashlight(View view) {
    }


    public void clipBarcode(String bar) {
        ClipboardManager clipboardManager = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clipData = ClipData.newPlainText("Barcode", bar);
        clipboardManager.setPrimaryClip(clipData);
        Uri uri = Uri.parse("https://consumer.go.kr/user/ftc/consumer/goodsinfo/57/selectGoodsInfoList.do?tabType=01");
        Intent mIntent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(mIntent);
    }


}
