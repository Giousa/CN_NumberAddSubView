package cniao5.com.numberaddsubview;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v7.internal.widget.TintTypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class NumberAddSubView extends LinearLayout implements View.OnClickListener {


    private LayoutInflater mInflater;

    private Button mBtnAdd;
    private Button mBtnSub;
    private TextView mTextNum;


    private int value;
    private int minValue;
    private int maxValue;

    private  OnButtonClickListener mOnButtonClickListener;



    public NumberAddSubView(Context context) {
        this(context,null);
    }

    public NumberAddSubView(Context context, AttributeSet attrs) {

        this(context,attrs,0);
    }

    public NumberAddSubView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        mInflater = LayoutInflater.from(context);

        initView();


        if(attrs!=null){

            TintTypedArray a = TintTypedArray.obtainStyledAttributes(context,attrs,R.styleable.NumberAddSubView,defStyleAttr,0);


            int val =  a.getInt(R.styleable.NumberAddSubView_value,0);
            setValue(val);


            int minVal =  a.getInt(R.styleable.NumberAddSubView_minValue,0);
            setMinValue(minVal);

            int maxVal =  a.getInt(R.styleable.NumberAddSubView_manValue,0);
            setMaxValue(maxVal);

            Drawable drawableBtnAdd =a.getDrawable(R.styleable.NumberAddSubView_btnAddBackground);
            Drawable drawableBtnSub =a.getDrawable(R.styleable.NumberAddSubView_btnSubBackground);
            Drawable drawableTextView =a.getDrawable(R.styleable.NumberAddSubView_textViewBackground);

            setButtonAddBackgroud(drawableBtnAdd);
            setButtonSubBackgroud(drawableBtnSub);
            setTexViewtBackground(drawableTextView);

            a.recycle();

        }

    }



    private  void initView(){

        View view = mInflater.inflate(R.layout.wieght_number_add_sub,this,true);

        mBtnAdd = (Button) view.findViewById(R.id.btn_add);
        mBtnSub = (Button) view.findViewById(R.id.btn_sub);
        mTextNum = (TextView) view.findViewById(R.id.txt_num);

        mBtnAdd.setOnClickListener(this);
        mBtnSub.setOnClickListener(this);


    }


    public int getValue() {

       String val =  mTextNum.getText().toString();

        if(val !=null && !"".equals(val))
            this.value = Integer.parseInt(val);

        return value;
    }

    public void setValue(int value) {
        mTextNum.setText(value+"");
        this.value = value;
    }

    public int getMinValue() {



        return minValue;
    }

    public void setMinValue(int minValue) {
        this.minValue = minValue;
    }

    public int getMaxValue() {
        return maxValue;
    }

    public void setMaxValue(int maxValue) {
        this.maxValue = maxValue;
    }


    public void setOnButtonClickListener(OnButtonClickListener onButtonClickListener) {
        this.mOnButtonClickListener = onButtonClickListener;
    }

    @Override
    public void onClick(View v) {



        if (v.getId() == R.id.btn_add) {

            numAdd();
            if (mOnButtonClickListener != null) {

                mOnButtonClickListener.onButtonAddClick(v,value);
            }

        } else if (v.getId() == R.id.btn_sub) {

            numSub();

            if (mOnButtonClickListener != null) {

                mOnButtonClickListener.onButtonSubClick(v,value);
            }
        }


    }



    private void numAdd(){

        if(value<maxValue)
            value=value+1;

        mTextNum.setText(value+"");
    }

    private void numSub(){

        if(value>minValue)
            value=value-1;

        mTextNum.setText(value+"");
    }



    public void setTexViewtBackground(Drawable drawable){

        mTextNum.setBackgroundDrawable(drawable);

    }


    public void setTextViewBackground(int drawableId){

        setTexViewtBackground(getResources().getDrawable(drawableId));

    }


    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public void setButtonAddBackgroud(Drawable backgroud){
        this.mBtnAdd.setBackground(backgroud);
    }


    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public void setButtonSubBackgroud(Drawable backgroud){
        this.mBtnSub.setBackground(backgroud);
    }





    public interface  OnButtonClickListener{

        void onButtonAddClick(View view,int value);
        void onButtonSubClick(View view,int value);
    }


}
