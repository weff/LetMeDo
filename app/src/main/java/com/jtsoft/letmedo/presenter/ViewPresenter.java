package com.jtsoft.letmedo.presenter;

import com.jtsoft.letmedo.view.CommonView;
import com.jtsoft.letmedo.common.OnNetRequestListener;
import com.jtsoft.letmedo.model.ViewModel;

/**
 * Created by admin on 2017/10/16.
 */

public class ViewPresenter  extends BasePresenter<CommonView, ViewModel> {

    public void getPayOrder(Integer orderId, final int payType) {
        addSubscriber(getViewModel()
                .getPayOrder(orderId, payType,new OnNetRequestListener(mMvpView, false)
                ));
    }
}
