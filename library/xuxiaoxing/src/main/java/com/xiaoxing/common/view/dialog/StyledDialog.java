package com.xiaoxing.common.view.dialog;


import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatDialog;
import android.view.View;

import com.xiaoxing.common.view.dialog.config.ConfigBean;
import com.xiaoxing.common.view.dialog.interfaces.MyDialogListener;
import com.xiaoxing.common.view.dialog.interfaces.MyItemDialogListener;

import java.util.List;

/**
 * Created by Administrator on 2016/5/4 0004.
 */
public class StyledDialog  {

    //android.support.v7.app.

    public static Context context;

    private static int singleChosen;

    public static DialogInterface getLoadingDialog() {
        return loadingDialog;
    }

    private static DialogInterface loadingDialog;//缓存加载中的dialog,便于以后可以不需要对象就让它消失

    private static boolean isMiUi8 = false;//miui8用非activity的Context时,无法以TYPE_TOAST的形式弹出对话框.没有好的解决办法.....

    public static Handler getMainHandler() {
        if(mainHandler==null){
            mainHandler = new Handler(Looper.getMainLooper());
        }
        return mainHandler;
    }

    private static Handler mainHandler;

    public static void init(Context context){
        StyledDialog.context = context;
       /* String userAgent = System.getProperty("http.agent");
        if((!TextUtils.isEmpty(userAgent)) && userAgent.contains("MIUI") && userAgent.contains("V8")){
            isMiUi8 = true;
        }*/
       mainHandler = new Handler(Looper.getMainLooper());

    }

     public static void setLoadingObj(DialogInterface  loading){
        dismiss(loadingDialog);
        loadingDialog = loading;
    }




    /**
     * 一键让loading消失.
     */
    public static void dismissLoading(){
        if (loadingDialog != null ){
            dismiss(loadingDialog);
            loadingDialog = null;
        }
    }

   

    public static void dismiss(DialogInterface... dialogs){
        if (dialogs != null && dialogs.length>0){
           for (DialogInterface dialog : dialogs){
               if (dialog instanceof Dialog){
                   Dialog dialog1 = (Dialog) dialog;
                   if (dialog1.isShowing()){
                       dialog1.dismiss();
                   }
               }else if (dialog instanceof AppCompatDialog){
                   AppCompatDialog dialog2 = (AppCompatDialog) dialog;
                   if (dialog2.isShowing()){
                       dialog2.dismiss();
                   }
               }
           }
            
        }
    }


    public static ConfigBean buildProgress(CharSequence msg, boolean isHorizontal) {
        return DialogAssigner.getInstance().assignProgress(null,msg,isHorizontal);
    }

    /**
     *  可以在任何线程调用
     * @param dialog 传入show方法返回的对象
     * @param progress
     * @param max
     * @param msg 如果是转圈圈,会将msg变成msg:78%的形式.如果是水平,msg不起作用
     * @param isHorizontal 是水平线状,还是转圈圈
     */
    public static void updateProgress(final Dialog dialog, final int progress, final int max, final CharSequence msg, final boolean isHorizontal){

        getMainHandler().post(new Runnable() {
            @Override
            public void run() {
                if(dialog instanceof ProgressDialog && dialog.isShowing()){
                    ProgressDialog progressDialog = (ProgressDialog) dialog;
                    if(isHorizontal){
                        progressDialog.setProgress((int) progress);
                        progressDialog.setMax((int) max);
                    }else {
                        String pmsg = new StringBuilder(msg).append(":").append(progress*100/max).append("%").toString();
                        progressDialog.setMessage(pmsg);
                    }

                }
            }
        });


    }

    public static ConfigBean buildLoading( CharSequence msg) {
        return DialogAssigner.getInstance().assignLoading(null,msg,true,false);
    }
    public static ConfigBean buildLoading( ) {
        return DialogAssigner.getInstance().assignLoading(null,"加载中...",true,false);
    }

    public static ConfigBean buildMdLoading( ) {
        return DialogAssigner.getInstance().assignMdLoading(null,"加载中...",true,false);
    }
    
    public static ConfigBean buildMdLoading( CharSequence msg) {
        return DialogAssigner.getInstance().assignMdLoading(null,msg,true,false);
    }

     
    public static ConfigBean buildMdAlert( CharSequence title, CharSequence msg, MyDialogListener listener) {
        return DialogAssigner.getInstance().assignMdAlert(null,title,msg,listener);
    }

    
    public static ConfigBean buildMdSingleChoose( CharSequence title, int defaultChosen, CharSequence[] words, MyItemDialogListener listener) {
        return DialogAssigner.getInstance().assignMdSingleChoose(null,title,defaultChosen,words,listener);
    }

    
    public static ConfigBean buildMdMultiChoose( CharSequence title, CharSequence[] words, boolean[] checkedItems, MyDialogListener btnListener) {
        return DialogAssigner.getInstance().assignMdMultiChoose(null,title,words,checkedItems,btnListener);
    }

   
    public static ConfigBean buildIosAlert( CharSequence title, CharSequence msg, MyDialogListener listener) {
        return DialogAssigner.getInstance().assignIosAlert(null,title,msg,listener);
    }

   
    public static ConfigBean buildIosAlertVertical( CharSequence title, CharSequence msg, MyDialogListener listener) {
        return DialogAssigner.getInstance().assignIosAlertVertical(null,title,msg,listener);
    }

    
    public static ConfigBean buildIosSingleChoose( List<? extends CharSequence> words, MyItemDialogListener listener) {
        return DialogAssigner.getInstance().assignIosSingleChoose(null,words,listener);
    }

    
    public static ConfigBean buildBottomItemDialog( List<? extends CharSequence> words, CharSequence bottomTxt, MyItemDialogListener listener) {
        return DialogAssigner.getInstance().assignBottomItemDialog(null,words,bottomTxt,listener);
    }

   
    public static ConfigBean buildNormalInput( CharSequence title, CharSequence hint1, CharSequence hint2, CharSequence firstTxt, CharSequence secondTxt, MyDialogListener listener) {
        return DialogAssigner.getInstance().assignNormalInput(null,title,hint1,hint2,firstTxt,secondTxt,listener);
    }

    public static ConfigBean buildCustom( View contentView, int gravity) {
        return DialogAssigner.getInstance().assignCustom(null,contentView,gravity);
    }

    public static ConfigBean buildCustomBottomSheet( View contentView){
        return DialogAssigner.getInstance().assignCustomBottomSheet(null,contentView);
    }

    public static ConfigBean buildBottomSheetLv(CharSequence title, List datas, CharSequence bottomTxt, MyItemDialogListener listener){
        return DialogAssigner.getInstance().assignBottomSheetLv(null,title,datas,bottomTxt,listener);
    }

    public static ConfigBean buildBottomSheetGv( CharSequence title, List datas, CharSequence bottomTxt,int columnsNum ,MyItemDialogListener listener){
        return DialogAssigner.getInstance().assignBottomSheetGv(null,title,datas,bottomTxt,columnsNum,listener);
    }

}
