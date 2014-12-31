package com.roc.annotation;

import java.lang.annotation.Annotation;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * View控件的{@link Annotation}<br>
 * 使用范围为全局变量<br>
 * listenerClass一般使用默认值
 * <p/>
 * <p>
 * 使用示例：
 * </p>
 * <p/>
 * <pre class="prettyprint">
 * public class AnnotationTest extends Activity implements OnClickListener, OnItemClickListener, OnItemSelectedListener,
 * OnItemLongClickListener
 * {
 * &#064;InitView({@link #id} = {R.id.button1, R.id.button2}, {@link #onClickListener} = true)
 * private Button btn1, btn2;
 * &#064;InitView({@link #id} = R.id.listview, {@link #onItemClickListener} = true, {@link #onItemLongClickListener}= true)
 * private ListView listView;
 * &#064;InitView({@link #id} = R.id.spinner1, {@link #onItemSelectedListener} = true, {@link #listenerClass} = MyItenSelectClickListener.class)
 * private Spinner spinner;
 * <p/>
 * &#064;Override
 * protected void onCreate(Bundle savedInstanceState)
 * {
 * super.onCreate(savedInstanceState);
 * setContentView(R.layout.activity_annotation);
 * // 加载控件
 * ViewInstaller.processAnnotation(this);
 * }
 * <p/>
 * private void otherMethod()
 * {
 * // 控件操作
 * btn.setText(&quot;test&quot;);
 * ......
 * }
 * <p/>
 * &#064;Override
 * public void onClick(View v)
 * {
 * Toast.makeText(this, &quot;onClick&quot;, 0).show();
 * }
 * <p/>
 * &#064;Override
 * public void onItemClick(AdapterView&lt;?&gt; parent, View view, int position, long id)
 * {
 * Toast.makeText(this, &quot;item click&quot;, 0).show();
 * }
 * <p/>
 * &#064;Override
 * public boolean onItemLongClick(AdapterView&lt;?&gt; parent, View view, int position, long id)
 * {
 * Toast.makeText(this, &quot;onItemLongClick&quot;, 0).show();
 * return false;
 * }
 * <p/>
 * <pre class="prettyprint">
 * private class MyItenSelectClickListener implements OnItemSelectedListener {
 * public MyItenSelectClickListener() {
 * // 要求有构造方法
 * }
 * <p/>
 * &#064;Override
 * public void onItemSelected(AdapterView&lt;?&gt; parent, View view, int position, long id) {
 * Toast.makeText(AnnotationTest.this, &quot;MyItenSelectClickListener onItemSelected&quot;, 0).show();
 * }
 * <p/>
 * &#064;Override
 * public void onNothingSelected(AdapterView&lt;?&gt; parent) {
 * <p/>
 * }
 * }
 * </pre>
 * <p/>
 * } </pre>
 *
 * @author Mr.Zheng
 * @date 2014年8月11日13:27:31
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.LOCAL_VARIABLE})
public @interface InitView {

    /**
     * View控件的id<br>
     * 大于一个时如果有监听则全部使用同一个监听实例
     */
    int id();

    /**
     * 是否添加单击事件监听<br>
     * {@link android.view.View.OnClickListener}
     */
    boolean onClickListener() default false;

    /**
     * 是否添加长按事件监听<br>
     * {@link android.view.View.OnLongClickListener}
     */
    boolean onLongClickListener() default false;

    /**
     * 是否添加item点击事件监听<br>
     * {@link android.widget.AdapterView.OnItemClickListener}
     */
    boolean onItemClickListener() default false;

    /**
     * 是否添加item长按事件监听<br>
     * {@link android.widget.AdapterView.OnItemLongClickListener}
     */
    boolean onItemLongClickListener() default false;

    /**
     * 是否添加item选择事件监听<br>
     * {@link android.widget.AdapterView.OnItemSelectedListener}
     */
    boolean onItemSelectedListener() default false;

    /**
     * 是否添加{@link android.widget.CompoundButton}的item选择改变时的事件监听<br>
     * CompoundButton: CheckBox、RadioButton,、Switch、ToggleButton<br>
     *
     * @see {@link android.widget.CheckBox}<br>
     * {@link android.widget.RadioButton}<br>
     * {@link android.widget.Switch}<br>
     * {@link android.widget.ToggleButton}<br>
     * <br>
     */
    boolean onCompoundButtonCheckedChangeListener() default false;

    /**
     * 是否添加{@link android.widget.RadioGroup}的item选择改变时的事件监听<br>
     * RadioGroup：RadioGroup
     */
    boolean onRadioGroupCheckedChangeListener() default false;

    /**
     * 监听器所在类,默认值为{@link #InitView}.class,即默认将 {@link
     * ViewInstaller.processAnnotation(Object obj)} 中的obj对象作为listener监听器实例对象<br>
     * 推荐使用默认值的方法<br>
     * eg:<br>
     * 1:使用默认值 <br>
     * 2:使用内部类（可以是静态内部类）时要求监听类有构造方法且为public所修饰，否则会抛出异常<br>
     * 3:使用一般类，无特殊要求
     *
     * @bug 使用内部类做监听时，且做了多个动作监听，目前需要把监听回调方法都放在一个listenerClass里
     * @see ViewInstaller
     */
    Class listenerClass() default InitView.class;
}
