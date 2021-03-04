package com.lz.regex;

import java.util.regex.Pattern;

/**  
 *
 *  @author	Leowy Zhang
 */
public class RegexUtils {

	static String str_jianzhi = "(在家(即可|手机|就职|可做)|日.?结|日.?算|手机家中|芝麻分优|信.?誉|网.?[购够購]|[刷唰放拍].?([单單単]|[销宵硝]量)|[评呯苹平泙評][語语论]|上.?量|加优泙量|(职位|简历).*通过|[\\u4e00-\\u9fa5][\\dO]{2,3}[^\\d日 点]?[-~][\\dO]{2,3}|[①一每][天日曰][\\dO]{2,3}|[\\dO]{2,3}[一每/][天日曰単]).*([QqＱ筘扣口].{0,8}\\d{5,}|pp群.{0,8}\\d{5,}|[微Vv∨威薇Ⅴ].{0,8}\\w{5,})";
	static final Pattern p_jianzhi = Pattern.compile(str_jianzhi, Pattern.CASE_INSENSITIVE);

	static String str_daikuan = "(款.{0,2}项|借.{0,2}贷|贷.{0,2}款|借.{0,2}款|额.{0,2}度|額.{0,2}度|信.{0,2}贷|下.{0,2}款|(贷|一笔|款项|普惠|融360).*审核|薇粒袋).+(微.?信|Q.?Q|q.?q|V.?信|扣扣|加扣|客服Q|[vw]x).*([0-9a-zA-Z]{5,}||同号)";
	static final Pattern p_daikuan = Pattern.compile(str_daikuan, Pattern.CASE_INSENSITIVE);

	static String str_jipiao = "(航班|飞机|旅客|航空).*(取消|延期|调整|故障|停飞)";
	static final Pattern p_jipiao = Pattern.compile(str_jipiao, Pattern.CASE_INSENSITIVE);

	static final Pattern p_qq = Pattern.compile("(Q.?Q|q.?q|V.?|Ｑ.?Ｑ|[vw]x).*(\\w{5,})", Pattern.CASE_INSENSITIVE);

	static final Pattern p_replace = Pattern.compile("\n|'|\r|\\(|\\)");

	public static String filterBlank(String str) {
		return p_replace.matcher(str).replaceAll("");
	}

	public static boolean isQQ(String str) {
		boolean rc1 = p_qq.matcher(str).find();
		return rc1;
	}

	public static boolean isMatch(String str) {
		boolean rc1 = Pattern.matches(str_jianzhi, str);
		boolean rc2 = Pattern.matches(str_daikuan, str);
		boolean rc3 = Pattern.matches(str_jipiao, str);
		return rc1 || rc2 || rc3;
	}

	public static boolean isFind(String str) {
		boolean rc1 = p_jianzhi.matcher(str).find();
		boolean rc2 = p_daikuan.matcher(str).find();
		boolean rc3 = p_jipiao.matcher(str).find();
		return rc1 || rc2 || rc3;
	}

	public static void main(String[] args) {
		
		System.out.println(isQQ("qq123457"));
		
		//部分匹配
		System.out.println(isMatch("东方航空取消95530"));
		System.out.println(isFind("东方航空取消95530"));
		
		//全匹配
		System.out.println(isMatch("贷款 QQ abcef"));
		System.out.println(isFind("贷款 QQ abcef"));
		
	}

}
