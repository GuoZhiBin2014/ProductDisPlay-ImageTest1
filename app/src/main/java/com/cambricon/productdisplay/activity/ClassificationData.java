package com.cambricon.productdisplay.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cambricon.productdisplay.R;

import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.chart.PointStyle;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.model.XYSeries;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;

/**
 * Created by dell on 18-2-8.
 */

public class ClassificationData extends Fragment {
    private View view;
    private Context context;
    private String content;
    private LinearLayout linearLayout;
    private GraphicalView graphicalView;
    public ClassificationData(){

    }

    @SuppressLint("ValidFragment")
    public ClassificationData(Context contexts, String content) {
        this.context = contexts;
        this.content = content;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.classification_data_layout, null);
        init();
        showChart();
        return view;
    }
    private void init(){
        linearLayout=view.findViewById(R.id.chart_line);
    }

    private void showChart() {
        XYMultipleSeriesDataset mDataSet=getDataSet();
        XYMultipleSeriesRenderer mRefender=getRefender();
        graphicalView= ChartFactory.getLineChartView(getContext(), mDataSet, mRefender);
        linearLayout.addView(graphicalView);
    }
    private XYMultipleSeriesDataset getDataSet() {
        XYMultipleSeriesDataset seriesDataset=new XYMultipleSeriesDataset();
        XYSeries xySeries1=new XYSeries(getContext().getResources().getString(R.string.classification_chart_desc));
        xySeries1.add(1, 36);
        xySeries1.add(2, 30);
        xySeries1.add(3, 27);
        xySeries1.add(4, 29);
        xySeries1.add(5, 34);
        xySeries1.add(6, 28);
        xySeries1.add(7, 33);
        xySeries1.add(8, 32);
        xySeries1.add(9, 30);
        xySeries1.add(10, 34);
        seriesDataset.addSeries(xySeries1);

        return seriesDataset;
    }
    private XYMultipleSeriesRenderer getRefender() {
        /*描绘器，设置图表整体效果，比如x,y轴效果，缩放比例，颜色设置*/
        XYMultipleSeriesRenderer seriesRenderer=new XYMultipleSeriesRenderer();

        seriesRenderer.setChartTitleTextSize(50);//设置图表标题的字体大小(图的最上面文字)
        seriesRenderer.setMargins(new int[] { 60, 40, 40, 40 });//设置外边距，顺序为：上左下右
        //坐标轴设置
        seriesRenderer.setAxisTitleTextSize(30);//设置坐标轴标题字体的大小
        seriesRenderer.setYAxisMin(0);//设置y轴的起始值
        seriesRenderer.setYAxisMax(200);//设置y轴的最大值
        seriesRenderer.setYLabels(10);//设置y轴显示点数
        seriesRenderer.setXAxisMin(0.5);//设置x轴起始值
        seriesRenderer.setXAxisMax(10.5);//设置x轴最大值
        seriesRenderer.setYTitle(getActivity().getResources().getString(R.string.classification_chart_y));//设置y轴标题
        //seriesRenderer.setXTitle(getActivity().getResources().getString(R.string.classification_chart_x));//设置x轴标题
        //颜色设置
        seriesRenderer.setApplyBackgroundColor(true);//是应用设置的背景颜色
        seriesRenderer.setLabelsColor(0xFF85848D);//设置标签颜色
        seriesRenderer.setBackgroundColor(Color.argb(100, 255, 231, 224));//设置图表的背景颜色
        //缩放设置
        seriesRenderer.setZoomButtonsVisible(false);//设置缩放按钮是否可见
        seriesRenderer.setZoomEnabled(false); //图表是否可以缩放设置
        seriesRenderer.setZoomInLimitX(7);
        //图表移动设置
        seriesRenderer.setPanEnabled(false);//图表是否可以移动

        //坐标轴标签设置
        seriesRenderer.setLabelsTextSize(20);//设置标签字体大小
        seriesRenderer.setXLabelsAlign(Paint.Align.CENTER);
        seriesRenderer.setYLabelsAlign(Paint.Align.LEFT);
        seriesRenderer.setXLabels(0);//显示的x轴标签的个数
        seriesRenderer.addXTextLabel(1, "1");//针对特定的x轴值增加文本标签
        seriesRenderer.addXTextLabel(2, "2");
        seriesRenderer.addXTextLabel(3, "3");
        seriesRenderer.addXTextLabel(4, "4");
        seriesRenderer.addXTextLabel(5, "5");
        seriesRenderer.addXTextLabel(6, "6");
        seriesRenderer.addXTextLabel(7, "7");
        seriesRenderer.addXTextLabel(8, "8");
        seriesRenderer.addXTextLabel(9, "9");
        seriesRenderer.addXTextLabel(10, "10");
        seriesRenderer.setPointSize(5);//设置坐标点大小

        seriesRenderer.setMarginsColor(Color.WHITE);//设置外边距空间的颜色
        seriesRenderer.setClickEnabled(false);
        seriesRenderer.setChartTitle(getContext().getResources().getString(R.string.classification_chart_title));

        /*某一组数据的描绘器，描绘该组数据的个性化显示效果，主要是字体跟颜色的效果*/
        XYSeriesRenderer xySeriesRenderer1=new XYSeriesRenderer();
        xySeriesRenderer1.setAnnotationsColor(0xFFFF0000);//设置注释（注释可以着重标注某一坐标）的颜色
        xySeriesRenderer1.setAnnotationsTextAlign(Paint.Align.CENTER);//设置注释的位置
        xySeriesRenderer1.setAnnotationsTextSize(12);//设置注释文字的大小
        xySeriesRenderer1.setPointStyle(PointStyle.CIRCLE);//坐标点的显示风格
        xySeriesRenderer1.setFillPoints(true);
        xySeriesRenderer1.setPointStrokeWidth(3);//坐标点的大小
        xySeriesRenderer1.setColor(0xFFF46C48);//表示该组数据的图或线的颜色
        xySeriesRenderer1.setDisplayChartValues(true);//设置是否显示坐标点的y轴坐标值
        xySeriesRenderer1.setChartValuesTextSize(25);//设置显示的坐标点值的字体大小
        xySeriesRenderer1.setDisplayChartValuesDistance(30);

        seriesRenderer.addSeriesRenderer(xySeriesRenderer1);
        return seriesRenderer;
    }
}
