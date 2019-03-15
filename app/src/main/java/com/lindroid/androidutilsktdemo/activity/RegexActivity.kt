package com.lindroid.androidutilsktdemo.activity

import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.lindroid.androidutilskt.extension.*
import com.lindroid.androidutilsktdemo.R
import com.lindroid.androidutilsktdemo.base.BaseActivity
import com.youngfeng.snake.annotations.EnableDragToClose
import kotlinx.android.synthetic.main.activity_regex.*
import kotlin.properties.Delegates
import kotlin.reflect.KProperty

/**
 * @author Lin
 * @date 2019/3/15
 * @function 正则表达式
 * @Description
 */
@EnableDragToClose
class RegexActivity(override val contentViewId: Int = R.layout.activity_regex) : BaseActivity() {

    private lateinit var map: LinkedHashMap<String, String>


    private var content: String by Delegates.observable("") { property: KProperty<*>, oldValue: String, newValue: String ->
        //        content = etRegex.text.toString()
    }

    override fun initView() {
        super.initView()
        initToolBar(R.string.util_regex)
        initSpinner()

    }


    override fun initOnClick() {
        super.initOnClick()

        spRegex.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val key = map.keys.toList()[position]
                content = map[key] ?: ""
                etRegex.setText(content)


            }
        }

        btnMobile.setOnClickListener { shortToast("${getString(R.string.regex_mobile)}：${content.isMobile}") }
        btnEmail.setOnClickListener { shortToast("${getString(R.string.regex_email)}：${content.isEmail}") }
        btnUrl.setOnClickListener { shortToast("${getString(R.string.regex_url)}：${content.isWebUrl}") }
        btnNumber.setOnClickListener { shortToast("${getString(R.string.regex_number)}：${content.isNumber}") }
        btnPosInt.setOnClickListener { shortToast("${getString(R.string.regex_pos_int)}：${content.isPositiveInt}") }
        btnNegInt.setOnClickListener { shortToast("${getString(R.string.regex_neg_int)}：${content.isNegativeInt}") }
        btnLetter.setOnClickListener { shortToast("${getString(R.string.regex_letter)}：${content.isLetter}") }
        btnUpperCase.setOnClickListener { shortToast("${getString(R.string.regex_upper_case)}：${content.isUpperCaseLetter}") }
        btnLowerCase.setOnClickListener { shortToast("${getString(R.string.regex_lower_case)}：${content.isLowerCaseLetter}") }
        btnChinese.setOnClickListener { shortToast("${getString(R.string.regex_chinese)}：${content.isChinese}") }
        btnQQ.setOnClickListener { shortToast("${getString(R.string.regex_chinese)}：${content.isQQ}") }
        btnMacaoMobile.setOnClickListener { shortToast("${getString(R.string.regex_chinese)}：${content.isMacaoMobile}") }
    }

    private fun initSpinner() {
        map = linkedMapOf(
            getString(R.string.regex_mobile) to "13800138000",
            getString(R.string.regex_email) to "iamcoder@163.com",
            getString(R.string.regex_url) to "https://www.baidu.com/",
            getString(R.string.regex_number) to "012345",
            getString(R.string.regex_pos_int) to "1024",
            getString(R.string.regex_neg_int) to "-1024",
            getString(R.string.regex_letter) to "aBcD",
            getString(R.string.regex_upper_case) to "ABCDEFG",
            getString(R.string.regex_lower_case) to "abcdefg",
            getString(R.string.regex_chinese) to "https://www.baidu.com/",
            getString(R.string.regex_qq) to "1001",
            getString(R.string.regex_macao_mobile) to "65535"
        )
        val adapter = ArrayAdapter<String>(mContext, android.R.layout.simple_spinner_item, map.keys.toList())
        adapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice)
        spRegex.adapter = adapter
        content = map.keys.toList()[0]
    }


}
