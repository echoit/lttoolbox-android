package org.apertium.transfer.generated;
import java.io.*;
import org.apertium.transfer.*;
import org.apertium.interchunk.InterchunkWord;
public class apertium_eo_en_eo_en_t2x extends GeneratedTransferBase
{
	public boolean isOutputChunked()
	{
		return false;
	}
	ApertiumRE attr_a_chunk = new ApertiumRE("<S(?:N|D|V)>");
	ApertiumRE attr_gen = new ApertiumRE("<(?:mf|nt|GD|GN|f|m)>");
	ApertiumRE attr_nbr = new ApertiumRE("<(?:sg|pl|sp|ND)>");
	ApertiumRE attr_prs = new ApertiumRE("<(?:p1|p2|p3|PD)>");
	ApertiumRE attr_cas = new ApertiumRE("<(?:nom|acc)>");
	/**  normala verbo: specifa formo nur por p3/sg/pres  can-verbo: ĉiuj formoj egalaj por ĉiuj personoj  be-verbo: specifaj formoj por p1 kaj p3 / sg / pres kaj past  */
	ApertiumRE attr_a_vrb2 = new ApertiumRE("<(?:vbreg|can|be)>");
	ApertiumRE attr_a_tns = new ApertiumRE("<(?:pres|subs|pprs|past|pri|imp|inf|cni|ger|fti|pp)>");
	ApertiumRE attr_lem = new ApertiumRE("(([^<]|\"\\<\")+)");
	ApertiumRE attr_lemq = new ApertiumRE("\\#[- _][^<]+");
	ApertiumRE attr_lemh = new ApertiumRE("(([^<#]|\"\\<\"|\"\\#\")+)");
	ApertiumRE attr_whole = new ApertiumRE("(.+)");
	ApertiumRE attr_tags = new ApertiumRE("((<[^>]+>)+)");
	ApertiumRE attr_chname = new ApertiumRE("(\\{([^/]+)\\/)");
	ApertiumRE attr_chcontent = new ApertiumRE("(\\{.+)");
	ApertiumRE attr_content = new ApertiumRE("(\\{.+)");
	String var_pers = "";
	String var_nombre = "";
	String var_genere = "";
	String var_DEBUG = "";
	
	private void macro_determina_nbr_gen_SN(Writer out, InterchunkWord word1) throws IOException
	{
		if (debug) { logCall("macro_determina_nbr_gen_SN",  word1); } 
		/** To determine value of variables of gender and
  number and propagate to other rules.  Variables are
  not used again in this rule  */
		if (word1.tl(attr_nbr).equals("<pl>"))
		{
			var_nombre = "<pl>";
		}
		else
		{
			var_nombre = "<sg>";
		}
		if (word1.tl(attr_gen).equals("<f>"))
		{
			var_genere = "<f>";
		}
		else
		{
			var_genere = "<m>";
		}
	}
	
	private void macro_concord_SN_SV(Writer out, InterchunkWord word1, String blank1, InterchunkWord word2) throws IOException
	{
		if (debug) { logCall("macro_concord_SN_SV",  word1, blank1,  word2); } 
		/**  aldonas prs kaj nbr al SV   Tiu ĉi makroo estas principe uzebla por tradukoj el esperanto al aliaj lingvoj, kie la verbo akordas kun la subjekto  */
		if (word1.tl(attr_lem).equalsIgnoreCase("pron"))
		{
			/**  la subjekto estas pronomo: ĝi eble eksplicitan prs  */
			if (word2.tl(attr_prs).equals("<PD>"))
			{
				if (word1.tl(attr_prs).equals(""))
				{
					word2.tlSet(attr_prs, "<p3>");
				}
				else
				{
					word2.tlSet(attr_prs, word1.tl(attr_prs));
				}
			}
			if (word2.tl(attr_nbr).equals("<ND>"))
			{
				word2.tlSet(attr_nbr, word1.tl(attr_nbr));
			}
		}
		else
		{
			if (word1.tl(attr_nbr).equals("<ND>"))
			{
				if ((word2.tl(attr_a_tns).equals("<pres>")
    && word2.tl(attr_nbr).equals("<ND>")))
				{
					word1.tlSet(attr_nbr, "<pl>");
				}
				else
				if (word2.tl(attr_a_tns).equals("<pres>"))
				{
					word1.tlSet(attr_nbr, "<sg>");
				}
				else
				{
					word1.tlSet(attr_nbr, "<sg>");
				}
			}
			if (word2.tl(attr_prs).equals("<PD>"))
			{
				word2.tlSet(attr_prs, "<p3>");
			}
			if (word2.tl(attr_nbr).equals("<ND>"))
			{
				if (!word1.tl(attr_nbr).equals("<sp>"))
				{
					word2.tlSet(attr_nbr, word1.tl(attr_nbr));
				}
				else
				{
					word2.tlSet(attr_nbr, "<sg>");
				}
			}
		}
	}
	
	private void macro_simpligo_prs_nbr(Writer out, InterchunkWord word1) throws IOException
	{
		if (debug) { logCall("macro_simpligo_prs_nbr",  word1); } 
		/** forigas prs kaj nbr de SV, se ne necesaj  */
		var_DEBUG = "<0>";
		if ((word1.tl(attr_a_vrb2).equalsIgnoreCase("<be>")
    && !((word1.tl(attr_a_tns).equals("<past>")
    || word1.tl(attr_a_tns).equals("<pres>"))
    && (word1.tl(attr_prs).equals("<p1>")
    || word1.tl(attr_prs).equals("<p3>")
    || word1.tl(attr_prs).equals(""))
    && !word1.tl(attr_nbr).equals("<pl>"))))
		{
			word1.tlSet(attr_nbr, "");
			word1.tlSet(attr_prs, "");
			var_DEBUG = "<1>";
		}
		else
		if ((!word1.tl(attr_a_vrb2).equalsIgnoreCase("<be>")
    && (!(word1.tl(attr_a_tns).equals("<pres>")
    && word1.tl(attr_prs).equals("<p3>")
    && word1.tl(attr_nbr).equals("<sg>"))
    || word1.tl(attr_a_vrb2).equals("<can>"))))
		{
			word1.tlSet(attr_nbr, "");
			word1.tlSet(attr_prs, "");
			var_DEBUG = "<2>";
		}
		/** 
<otherwise>
<let><var n="DEBUG"/><lit-tag v="3"/></let>
<choose>
  <when>
    <test>
  <equal caseless="yes">
    <clip pos="1" part="a_vrb2"/>
    <lit-tag v="be"/>
  </equal>
    </test>
<let><var n="DEBUG"/><lit-tag v="4"/></let>
  </when>
<otherwise>
<let><var n="DEBUG"/><clip pos="1" part="a_vrb2"/></let>
</otherwise>
</choose>
</otherwise>
 fina revizio: se ial restas PD kaj ND, ni forigas ilin  */
		if (word1.tl(attr_nbr).equals("<ND>"))
		{
			word1.tlSet(attr_nbr, "");
		}
		if (word1.tl(attr_prs).equals("<PD>"))
		{
			word1.tlSet(attr_prs, "");
		}
	}
	
	// REGLA: SA
	public void rule0__SA(Writer out, InterchunkWord word1) throws IOException
	{
		if (debug) { logCall("rule0__SA",  word1); } 
		{
			String myword = 
			         word1.tl(attr_whole)
			         ;
			if (myword.length()>0)
			{
				out.append('^');
				out.append(myword);
				out.append('$');
			}
		}
	}
	
	// REGLA: SN
	public void rule1__SN(Writer out, InterchunkWord word1) throws IOException
	{
		if (debug) { logCall("rule1__SN",  word1); } 
		{
			String myword = 
			         word1.tl(attr_whole)
			         ;
			if (myword.length()>0)
			{
				out.append('^');
				out.append(myword);
				out.append('$');
			}
		}
	}
	
	/** 
    <b pos="1"/>
          <chunk>
            <clip pos="1" part="lem"/>
            <clip pos="1" part="tags"/>
      <get-case-from pos="2">
              <clip pos="1" part="chcontent"/>
      </get-case-from>
          </chunk>
 anglalingvaj demandoj: necesas la mala direkto
    <rule comment="REGLA: adv do SN SV - how did you work - kiel vi laboris">
      <pattern>
        <pattern-item n="Adv_aux_SN_aux_konstatovorto"/>
        <pattern-item n="vbdoSV"/>
        <pattern-item n="SN"/>
        <pattern-item n="SV"/>
      </pattern>
      <action>
        <out>
    <chunk><clip pos="1" part="whole"/></chunk>
    <b pos="1"/>
    <b pos="2"/>
    <chunk><clip pos="3" part="whole"/></chunk>
    <b pos="3"/>
    <chunk><clip pos="4" part="lem"/><lit-tag v="SV"/><lit-tag v="fArU"/><clip pos="2" part="a_tns"/><clip pos="4" part="chcontent"/></chunk>
        </out>
      </action>
    </rule>


    <rule comment="REGLA: do SN SV - do you know the cat?">
      <pattern>
        <pattern-item n="vbdoSV"/>
        <pattern-item n="SN"/>
        <pattern-item n="SV"/>
      </pattern>
      <action>
        <out>
      <chunk>
      <lit v="ĉu"/><lit-tag v="adv.itg"/>
      <lit v="{^"/><get-case-from pos="1"><lit v="ĉu"/></get-case-from><lit-tag v="adv.itg"/><lit v="$}"/>
      </chunk>
    <b pos="1"/>
    <chunk><clip pos="2" part="whole"/></chunk>
    <b pos="2"/>
    <chunk><clip pos="3" part="whole"/></chunk>
        </out>
      </action>
    </rule>
 */
	// REGLA: Adv SV SN SN
	public void rule2__Adv__SVall__SN__SN(Writer out, InterchunkWord word1, String blank1, InterchunkWord word2, String blank2, InterchunkWord word3, String blank3, InterchunkWord word4) throws IOException
	{
		if (debug) { logCall("rule2__Adv__SVall__SN__SN",  word1, blank1,  word2, blank2,  word3, blank3,  word4); } 
		{
			String myword = 
			         word1.tl(attr_whole)
			         ;
			if (myword.length()>0)
			{
				out.append('^');
				out.append(myword);
				out.append('$');
			}
		}
		out.append(blank1);
		{
			String myword = 
			         word2.tl(attr_whole)
			         ;
			if (myword.length()>0)
			{
				out.append('^');
				out.append(myword);
				out.append('$');
			}
		}
		out.append(blank2);
		{
			String myword = 
			         word3.tl(attr_whole)
			         ;
			if (myword.length()>0)
			{
				out.append('^');
				out.append(myword);
				out.append('$');
			}
		}
		out.append(blank3);
		{
			String myword = 
			         word4.tl(attr_whole)
			         ;
			if (myword.length()>0)
			{
				out.append('^');
				out.append(myword);
				out.append('$');
			}
		}
	}
	
	/**  komenco de reguloj serĉantaj ĉefe la subjekton  */
	// REGLA: SN SV
	public void rule3__SNnom__SVall(Writer out, InterchunkWord word1, String blank1, InterchunkWord word2) throws IOException
	{
		if (debug) { logCall("rule3__SNnom__SVall",  word1, blank1,  word2); } 
		macro_concord_SN_SV(out, word1, blank1, word2);
		macro_simpligo_prs_nbr(out, word2);
		/** 
        <call-macro n="posadet_SN"><with-param pos="1"/></call-macro>
       */
		if (word2.tl(attr_a_tns).equals("<inf>"))
		{
			word2.tlSet(attr_a_tns, "<pres>");
		}
		{
			String myword = 
			         word1.tl(attr_whole)
			         ;
			if (myword.length()>0)
			{
				out.append('^');
				out.append(myword);
				out.append('$');
			}
		}
		out.append(blank1);
		{
			String myword = 
			         word2.tl(attr_whole)
			         ;
			if (myword.length()>0)
			{
				out.append('^');
				out.append(myword);
				out.append('$');
			}
		}
		out.append("^<REGULO: SN SV>");
		out.append(var_DEBUG);
		out.append('$');
		macro_determina_nbr_gen_SN(out, word1);
	}
	
	// REGLA: SN SADV SV
	public void rule4__SNnom__SADV__SVall(Writer out, InterchunkWord word1, String blank1, InterchunkWord word2, String blank2, InterchunkWord word3) throws IOException
	{
		if (debug) { logCall("rule4__SNnom__SADV__SVall",  word1, blank1,  word2, blank2,  word3); } 
		macro_concord_SN_SV(out, word1, blank2, word3);
		macro_simpligo_prs_nbr(out, word3);
		/** 
  <call-macro n="posadet_SN">
    <with-param pos="1"/>
  </call-macro>
 */
		if (word3.tl(attr_a_tns).equals("<inf>"))
		{
			word3.tlSet(attr_a_tns, "<pres>");
		}
		{
			String myword = 
			         word1.tl(attr_whole)
			         ;
			if (myword.length()>0)
			{
				out.append('^');
				out.append(myword);
				out.append('$');
			}
		}
		out.append(blank1);
		{
			String myword = 
			         word2.tl(attr_whole)
			         ;
			if (myword.length()>0)
			{
				out.append('^');
				out.append(myword);
				out.append('$');
			}
		}
		out.append(blank2);
		{
			String myword = 
			         word3.tl(attr_whole)
			         ;
			if (myword.length()>0)
			{
				out.append('^');
				out.append(myword);
				out.append('$');
			}
		}
		/** 
<chunk><lit-tag v="REGULO: SN SADV SV"/><var n="DEBUG"/></chunk>
 */
		macro_determina_nbr_gen_SN(out, word1);
	}
	
	// REGLA: SN SADV SADV SV
	public void rule5__SNnom__SADV__SADV__SVall(Writer out, InterchunkWord word1, String blank1, InterchunkWord word2, String blank2, InterchunkWord word3, String blank3, InterchunkWord word4) throws IOException
	{
		if (debug) { logCall("rule5__SNnom__SADV__SADV__SVall",  word1, blank1,  word2, blank2,  word3, blank3,  word4); } 
		macro_concord_SN_SV(out, word1, blank3, word4);
		macro_simpligo_prs_nbr(out, word4);
		/** 
  <call-macro n="posadet_SN"><with-param pos="1"/></call-macro>
 */
		if (word4.tl(attr_a_tns).equals("<inf>"))
		{
			word4.tlSet(attr_a_tns, "<pres>");
		}
		{
			String myword = 
			         word1.tl(attr_whole)
			         ;
			if (myword.length()>0)
			{
				out.append('^');
				out.append(myword);
				out.append('$');
			}
		}
		out.append(blank1);
		{
			String myword = 
			         word2.tl(attr_whole)
			         ;
			if (myword.length()>0)
			{
				out.append('^');
				out.append(myword);
				out.append('$');
			}
		}
		out.append(blank2);
		{
			String myword = 
			         word3.tl(attr_whole)
			         ;
			if (myword.length()>0)
			{
				out.append('^');
				out.append(myword);
				out.append('$');
			}
		}
		out.append(blank3);
		{
			String myword = 
			         word4.tl(attr_whole)
			         ;
			if (myword.length()>0)
			{
				out.append('^');
				out.append(myword);
				out.append('$');
			}
		}
		/** 
<chunk><lit-tag v="REGULO: SN SADV SADV SV"/><var n="DEBUG"/></chunk>
 */
		macro_determina_nbr_gen_SN(out, word1);
	}
	
	// REGLA: SN SP SV
	public void rule6__SNnom__SP__SVall(Writer out, InterchunkWord word1, String blank1, InterchunkWord word2, String blank2, InterchunkWord word3) throws IOException
	{
		if (debug) { logCall("rule6__SNnom__SP__SVall",  word1, blank1,  word2, blank2,  word3); } 
		macro_concord_SN_SV(out, word1, blank2, word3);
		macro_simpligo_prs_nbr(out, word3);
		/**     <call-macro n="posadet_SN"><with-param pos="1"/></call-macro>    */
		if (word3.tl(attr_a_tns).equals("<inf>"))
		{
			word3.tlSet(attr_a_tns, "<pres>");
		}
		{
			String myword = 
			         word1.tl(attr_whole)
			         ;
			if (myword.length()>0)
			{
				out.append('^');
				out.append(myword);
				out.append('$');
			}
		}
		out.append(blank1);
		{
			String myword = 
			         word2.tl(attr_whole)
			         ;
			if (myword.length()>0)
			{
				out.append('^');
				out.append(myword);
				out.append('$');
			}
		}
		out.append(blank2);
		{
			String myword = 
			         word3.tl(attr_whole)
			         ;
			if (myword.length()>0)
			{
				out.append('^');
				out.append(myword);
				out.append('$');
			}
		}
		/**  <chunk><lit-tag v="REGULO: SN SP SV"/><var n="DEBUG"/></chunk>  */
		macro_determina_nbr_gen_SN(out, word1);
	}
	
	// REGLA: SN SP SADV SV
	public void rule7__SNnom__SP__SADV__SVall(Writer out, InterchunkWord word1, String blank1, InterchunkWord word2, String blank2, InterchunkWord word3, String blank3, InterchunkWord word4) throws IOException
	{
		if (debug) { logCall("rule7__SNnom__SP__SADV__SVall",  word1, blank1,  word2, blank2,  word3, blank3,  word4); } 
		macro_concord_SN_SV(out, word1, blank3, word4);
		macro_simpligo_prs_nbr(out, word4);
		/**    <call-macro n="posadet_SN"><with-param pos="1"/></call-macro>  */
		if (word4.tl(attr_a_tns).equals("<inf>"))
		{
			word4.tlSet(attr_a_tns, "<pres>");
		}
		{
			String myword = 
			         word1.tl(attr_whole)
			         ;
			if (myword.length()>0)
			{
				out.append('^');
				out.append(myword);
				out.append('$');
			}
		}
		out.append(blank1);
		{
			String myword = 
			         word2.tl(attr_whole)
			         ;
			if (myword.length()>0)
			{
				out.append('^');
				out.append(myword);
				out.append('$');
			}
		}
		out.append(blank2);
		{
			String myword = 
			         word3.tl(attr_whole)
			         ;
			if (myword.length()>0)
			{
				out.append('^');
				out.append(myword);
				out.append('$');
			}
		}
		out.append(blank3);
		{
			String myword = 
			         word4.tl(attr_whole)
			         ;
			if (myword.length()>0)
			{
				out.append('^');
				out.append(myword);
				out.append('$');
			}
		}
		/**  <chunk><lit-tag v="REGULO: SN SP SADV SV"/><var n="DEBUG"/></chunk>  */
		macro_determina_nbr_gen_SN(out, word1);
	}
	
	// REGLA: SN SP SP SV
	public void rule8__SNnom__SP__SP__SVall(Writer out, InterchunkWord word1, String blank1, InterchunkWord word2, String blank2, InterchunkWord word3, String blank3, InterchunkWord word4) throws IOException
	{
		if (debug) { logCall("rule8__SNnom__SP__SP__SVall",  word1, blank1,  word2, blank2,  word3, blank3,  word4); } 
		macro_concord_SN_SV(out, word1, blank3, word4);
		macro_simpligo_prs_nbr(out, word4);
		/**    <call-macro n="posadet_SN"><with-param pos="1"/></call-macro>  */
		if (word4.tl(attr_a_tns).equals("<inf>"))
		{
			word4.tlSet(attr_a_tns, "<pres>");
		}
		{
			String myword = 
			         word1.tl(attr_whole)
			         ;
			if (myword.length()>0)
			{
				out.append('^');
				out.append(myword);
				out.append('$');
			}
		}
		out.append(blank1);
		{
			String myword = 
			         word2.tl(attr_whole)
			         ;
			if (myword.length()>0)
			{
				out.append('^');
				out.append(myword);
				out.append('$');
			}
		}
		out.append(blank2);
		{
			String myword = 
			         word3.tl(attr_whole)
			         ;
			if (myword.length()>0)
			{
				out.append('^');
				out.append(myword);
				out.append('$');
			}
		}
		out.append(blank3);
		{
			String myword = 
			         word4.tl(attr_whole)
			         ;
			if (myword.length()>0)
			{
				out.append('^');
				out.append(myword);
				out.append('$');
			}
		}
		/**  <chunk><lit-tag v="REGULO: SN SP SP SV"/><var n="DEBUG"/></chunk>  */
		macro_determina_nbr_gen_SN(out, word1);
	}
	
	// REGLA: SN SP SP SADV SV
	public void rule9__SNnom__SP__SP__SADV__SVall(Writer out, InterchunkWord word1, String blank1, InterchunkWord word2, String blank2, InterchunkWord word3, String blank3, InterchunkWord word4, String blank4, InterchunkWord word5) throws IOException
	{
		if (debug) { logCall("rule9__SNnom__SP__SP__SADV__SVall",  word1, blank1,  word2, blank2,  word3, blank3,  word4, blank4,  word5); } 
		macro_concord_SN_SV(out, word1, blank4, word5);
		macro_simpligo_prs_nbr(out, word5);
		/** 
  <call-macro n="posadet_SN">
    <with-param pos="1"/>
  </call-macro>
 */
		if (word5.tl(attr_a_tns).equals("<inf>"))
		{
			word5.tlSet(attr_a_tns, "<pres>");
		}
		{
			String myword = 
			         word1.tl(attr_whole)
			         ;
			if (myword.length()>0)
			{
				out.append('^');
				out.append(myword);
				out.append('$');
			}
		}
		out.append(blank1);
		{
			String myword = 
			         word2.tl(attr_whole)
			         ;
			if (myword.length()>0)
			{
				out.append('^');
				out.append(myword);
				out.append('$');
			}
		}
		out.append(blank2);
		{
			String myword = 
			         word3.tl(attr_whole)
			         ;
			if (myword.length()>0)
			{
				out.append('^');
				out.append(myword);
				out.append('$');
			}
		}
		out.append(blank3);
		{
			String myword = 
			         word4.tl(attr_whole)
			         ;
			if (myword.length()>0)
			{
				out.append('^');
				out.append(myword);
				out.append('$');
			}
		}
		out.append(blank4);
		{
			String myword = 
			         word5.tl(attr_whole)
			         ;
			if (myword.length()>0)
			{
				out.append('^');
				out.append(myword);
				out.append('$');
			}
		}
		/** 
<chunk>
<lit-tag v="REGULO: SN SP SP SADV SV"/>
<var n="DEBUG"/>
</chunk>
 */
		macro_determina_nbr_gen_SN(out, word1);
	}
	
	// REGLA: SN SP SP SP SV
	public void rule10__SNnom__SP__SP__SP__SVall(Writer out, InterchunkWord word1, String blank1, InterchunkWord word2, String blank2, InterchunkWord word3, String blank3, InterchunkWord word4, String blank4, InterchunkWord word5) throws IOException
	{
		if (debug) { logCall("rule10__SNnom__SP__SP__SP__SVall",  word1, blank1,  word2, blank2,  word3, blank3,  word4, blank4,  word5); } 
		macro_concord_SN_SV(out, word1, blank4, word5);
		macro_simpligo_prs_nbr(out, word5);
		/** 
  <call-macro n="posadet_SN">
    <with-param pos="1"/>
  </call-macro>
 */
		if (word5.tl(attr_a_tns).equals("<inf>"))
		{
			word5.tlSet(attr_a_tns, "<pres>");
		}
		{
			String myword = 
			         word1.tl(attr_whole)
			         ;
			if (myword.length()>0)
			{
				out.append('^');
				out.append(myword);
				out.append('$');
			}
		}
		out.append(blank1);
		{
			String myword = 
			         word2.tl(attr_whole)
			         ;
			if (myword.length()>0)
			{
				out.append('^');
				out.append(myword);
				out.append('$');
			}
		}
		out.append(blank2);
		{
			String myword = 
			         word3.tl(attr_whole)
			         ;
			if (myword.length()>0)
			{
				out.append('^');
				out.append(myword);
				out.append('$');
			}
		}
		out.append(blank3);
		{
			String myword = 
			         word4.tl(attr_whole)
			         ;
			if (myword.length()>0)
			{
				out.append('^');
				out.append(myword);
				out.append('$');
			}
		}
		out.append(blank4);
		{
			String myword = 
			         word5.tl(attr_whole)
			         ;
			if (myword.length()>0)
			{
				out.append('^');
				out.append(myword);
				out.append('$');
			}
		}
		/** 
<chunk>
<lit-tag v="REGULO: SN SP SP SP SV"/>
<var n="DEBUG"/>
</chunk>
 */
		macro_determina_nbr_gen_SN(out, word1);
	}
	
	// REGLA: SN SP SP SP SADV SV
	public void rule11__SNnom__SP__SP__SP__SADV__SVall(Writer out, InterchunkWord word1, String blank1, InterchunkWord word2, String blank2, InterchunkWord word3, String blank3, InterchunkWord word4, String blank4, InterchunkWord word5, String blank5, InterchunkWord word6) throws IOException
	{
		if (debug) { logCall("rule11__SNnom__SP__SP__SP__SADV__SVall",  word1, blank1,  word2, blank2,  word3, blank3,  word4, blank4,  word5, blank5,  word6); } 
		macro_concord_SN_SV(out, word1, blank5, word6);
		macro_simpligo_prs_nbr(out, word6);
		/** 
  <call-macro n="posadet_SN">
    <with-param pos="1"/>
  </call-macro>
 */
		if (word6.tl(attr_a_tns).equals("<inf>"))
		{
			word6.tlSet(attr_a_tns, "<pres>");
		}
		{
			String myword = 
			         word1.tl(attr_whole)
			         ;
			if (myword.length()>0)
			{
				out.append('^');
				out.append(myword);
				out.append('$');
			}
		}
		out.append(blank1);
		{
			String myword = 
			         word2.tl(attr_whole)
			         ;
			if (myword.length()>0)
			{
				out.append('^');
				out.append(myword);
				out.append('$');
			}
		}
		out.append(blank2);
		{
			String myword = 
			         word3.tl(attr_whole)
			         ;
			if (myword.length()>0)
			{
				out.append('^');
				out.append(myword);
				out.append('$');
			}
		}
		out.append(blank3);
		{
			String myword = 
			         word4.tl(attr_whole)
			         ;
			if (myword.length()>0)
			{
				out.append('^');
				out.append(myword);
				out.append('$');
			}
		}
		out.append(blank4);
		{
			String myword = 
			         word5.tl(attr_whole)
			         ;
			if (myword.length()>0)
			{
				out.append('^');
				out.append(myword);
				out.append('$');
			}
		}
		out.append(blank5);
		{
			String myword = 
			         word6.tl(attr_whole)
			         ;
			if (myword.length()>0)
			{
				out.append('^');
				out.append(myword);
				out.append('$');
			}
		}
		/** 
<chunk>
<lit-tag v="REGULO: SN SP SP SP SADV SV"/>
<var n="DEBUG"/>
</chunk>
 */
		macro_determina_nbr_gen_SN(out, word1);
	}
	
	// REGLA: SN, REL SV, SV - homo, kiu salutas, estas bona - folk, that greets, is good (duobla kunordigo) 
	public void rule12__SNnom__komo__REL__SV__komo__SVall(Writer out, InterchunkWord word1, String blank1, InterchunkWord word2, String blank2, InterchunkWord word3, String blank3, InterchunkWord word4, String blank4, InterchunkWord word5, String blank5, InterchunkWord word6) throws IOException
	{
		if (debug) { logCall("rule12__SNnom__komo__REL__SV__komo__SVall",  word1, blank1,  word2, blank2,  word3, blank3,  word4, blank4,  word5, blank5,  word6); } 
		macro_concord_SN_SV(out, word1, blank3, word4);
		macro_simpligo_prs_nbr(out, word4);
		macro_concord_SN_SV(out, word1, blank5, word6);
		macro_simpligo_prs_nbr(out, word6);
		/** 
  <call-macro n="posadet_SN">
    <with-param pos="1"/>
  </call-macro>
 */
		if (word6.tl(attr_a_tns).equals("<inf>"))
		{
			word6.tlSet(attr_a_tns, "<pres>");
		}
		{
			String myword = 
			         word1.tl(attr_whole)
			         ;
			if (myword.length()>0)
			{
				out.append('^');
				out.append(myword);
				out.append('$');
			}
		}
		out.append(blank1);
		{
			String myword = 
			         word2.tl(attr_whole)
			         ;
			if (myword.length()>0)
			{
				out.append('^');
				out.append(myword);
				out.append('$');
			}
		}
		out.append(blank2);
		{
			String myword = 
			         word3.tl(attr_whole)
			         ;
			if (myword.length()>0)
			{
				out.append('^');
				out.append(myword);
				out.append('$');
			}
		}
		out.append(blank3);
		{
			String myword = 
			         word4.tl(attr_whole)
			         ;
			if (myword.length()>0)
			{
				out.append('^');
				out.append(myword);
				out.append('$');
			}
		}
		out.append(blank4);
		{
			String myword = 
			         word5.tl(attr_whole)
			         ;
			if (myword.length()>0)
			{
				out.append('^');
				out.append(myword);
				out.append('$');
			}
		}
		out.append(blank5);
		{
			String myword = 
			         word6.tl(attr_whole)
			         ;
			if (myword.length()>0)
			{
				out.append('^');
				out.append(myword);
				out.append('$');
			}
		}
		/** 
<chunk>
<lit-tag v="REGULO: SN, REL SV, SV"/>
<var n="DEBUG"/>
</chunk>
 */
		macro_determina_nbr_gen_SN(out, word1);
	}
	
	// REGLA: SN SV SN - akuzativo
	public void rule13__SNnom__SVall__SNacc(Writer out, InterchunkWord word1, String blank1, InterchunkWord word2, String blank2, InterchunkWord word3) throws IOException
	{
		if (debug) { logCall("rule13__SNnom__SVall__SNacc",  word1, blank1,  word2, blank2,  word3); } 
		macro_concord_SN_SV(out, word1, blank1, word2);
		macro_simpligo_prs_nbr(out, word2);
		{
			String myword = 
			         word1.tl(attr_whole)
			         ;
			if (myword.length()>0)
			{
				out.append('^');
				out.append(myword);
				out.append('$');
			}
		}
		out.append(blank1);
		{
			String myword = 
			         word2.tl(attr_whole)
			         ;
			if (myword.length()>0)
			{
				out.append('^');
				out.append(myword);
				out.append('$');
			}
		}
		out.append(blank2);
		/**  ^det<SN><sp>{^a<det><ind><sg>$}$  Hector 09/12/16: Aldono de artikolo a. Mi kredas, ke en la plimulto de la okazoj ĝi estas trafa, tamen por la momento lasas flanken
      <choose>
  <when>
    <test>
      <and>
        <equal>
          <clip pos="3" part="nbr"/>
          <lit-tag v="sg"/>
        </equal>
        <equal>
          <clip pos="3" part="lem"/>
          <lit v="nom"/>
        </equal>
      </and>
    </test>
        <out>
    <chunk>
    <lit v="a"/><lit-tag v="SN.sp"/>
    <lit v="{^"/><lit v="a"/><lit-tag v="det.ind.sg"/><lit v="$}"/>
    </chunk>
          <b/>
        </out>
  </when>
      </choose>
 */
		out.append('^');
		out.append(word3.tl(attr_lem));
		out.append(word3.tl(attr_a_chunk));
		out.append(word3.tl(attr_prs));
		out.append(word3.tl(attr_gen));
		out.append(word3.tl(attr_nbr));
		out.append("<acc>");
		out.append(word3.tl(attr_chcontent));
		out.append("$^<REGULO: SN SV SN>");
		out.append(word3.tl(attr_lem));
		out.append(word3.tl(attr_nbr));
		out.append('$');
	}
	
	// REGLA: SN SP SV SN - akuzativo
	public void rule14__SNnom__SP__SVall__SNacc(Writer out, InterchunkWord word1, String blank1, InterchunkWord word2, String blank2, InterchunkWord word3, String blank3, InterchunkWord word4) throws IOException
	{
		if (debug) { logCall("rule14__SNnom__SP__SVall__SNacc",  word1, blank1,  word2, blank2,  word3, blank3,  word4); } 
		macro_concord_SN_SV(out, word1, blank2, word3);
		macro_simpligo_prs_nbr(out, word3);
		{
			String myword = 
			         word1.tl(attr_whole)
			         ;
			if (myword.length()>0)
			{
				out.append('^');
				out.append(myword);
				out.append('$');
			}
		}
		out.append(blank1);
		{
			String myword = 
			         word2.tl(attr_whole)
			         ;
			if (myword.length()>0)
			{
				out.append('^');
				out.append(myword);
				out.append('$');
			}
		}
		out.append(blank2);
		{
			String myword = 
			         word3.tl(attr_whole)
			         ;
			if (myword.length()>0)
			{
				out.append('^');
				out.append(myword);
				out.append('$');
			}
		}
		out.append(blank3);
		/**  ^det<SN><sp>{^a<det><ind><sg>$}$  Hector 09/12/16: Aldono de artikolo a. Mi kredas, ke en la plimulto de la okazoj ĝi estas trafa, tamen por la momento lasas flanken
      <choose>
  <when>
    <test>
      <and>
        <equal>
          <clip pos="4" part="nbr"/>
          <lit-tag v="sg"/>
        </equal>
        <equal>
          <clip pos="4" part="lem"/>
          <lit v="nom"/>
        </equal>
      </and>
    </test>
        <out>
    <chunk>
    <lit v="a"/><lit-tag v="SN.sp"/>
    <lit v="{^"/><lit v="a"/><lit-tag v="det.ind.sg"/><lit v="$}"/>
    </chunk>
          <b/>
        </out>
  </when>
      </choose>
 */
		out.append('^');
		out.append(word4.tl(attr_lem));
		out.append(word4.tl(attr_a_chunk));
		out.append(word4.tl(attr_prs));
		out.append(word4.tl(attr_gen));
		out.append(word4.tl(attr_nbr));
		out.append("<acc>");
		out.append(word4.tl(attr_chcontent));
		out.append("$^<REGULO: SN SP SV SN>");
		out.append(word4.tl(attr_lem));
		out.append(word4.tl(attr_nbr));
		out.append('$');
	}
	
	/**  komenco de postverbaj subjektoj  */
	// REGLA: SV SN
	public void rule15__SVall__SNnom(Writer out, InterchunkWord word1, String blank1, InterchunkWord word2) throws IOException
	{
		if (debug) { logCall("rule15__SVall__SNnom",  word1, blank1,  word2); } 
		macro_concord_SN_SV(out, word2, " ", word1);
		macro_simpligo_prs_nbr(out, word1);
		/** 
  <call-macro n="posadet_SN">
    <with-param pos="2"/>
  </call-macro>
 */
		if (word1.tl(attr_a_tns).equals("<inf>"))
		{
			word1.tlSet(attr_a_tns, "<pres>");
		}
		{
			String myword = 
			         word1.tl(attr_whole)
			         ;
			if (myword.length()>0)
			{
				out.append('^');
				out.append(myword);
				out.append('$');
			}
		}
		out.append(blank1);
		{
			String myword = 
			         word2.tl(attr_whole)
			         ;
			if (myword.length()>0)
			{
				out.append('^');
				out.append(myword);
				out.append('$');
			}
		}
		out.append("^<REGULO: SV SN>");
		out.append(var_DEBUG);
		out.append('$');
		macro_determina_nbr_gen_SN(out, word2);
	}
	
	// REGLA: SV SADV SN 
	public void rule16__SVall__SADV__SNnom(Writer out, InterchunkWord word1, String blank1, InterchunkWord word2, String blank2, InterchunkWord word3) throws IOException
	{
		if (debug) { logCall("rule16__SVall__SADV__SNnom",  word1, blank1,  word2, blank2,  word3); } 
		macro_concord_SN_SV(out, word3, " ", word1);
		macro_simpligo_prs_nbr(out, word1);
		/** 
  <call-macro n="posadet_SN">
    <with-param pos="3"/>
  </call-macro>
 */
		if (word1.tl(attr_a_tns).equals("<inf>"))
		{
			word1.tlSet(attr_a_tns, "<pres>");
		}
		{
			String myword = 
			         word1.tl(attr_whole)
			         ;
			if (myword.length()>0)
			{
				out.append('^');
				out.append(myword);
				out.append('$');
			}
		}
		out.append(blank1);
		{
			String myword = 
			         word2.tl(attr_whole)
			         ;
			if (myword.length()>0)
			{
				out.append('^');
				out.append(myword);
				out.append('$');
			}
		}
		out.append(blank2);
		{
			String myword = 
			         word3.tl(attr_whole)
			         ;
			if (myword.length()>0)
			{
				out.append('^');
				out.append(myword);
				out.append('$');
			}
		}
		/** 
<chunk>
<lit-tag v="REGULO: SV SADV SN"/>
<var n="DEBUG"/>
</chunk>
 */
		macro_determina_nbr_gen_SN(out, word3);
	}
	
	// REGLA: SV SP SN 
	public void rule17__SVall__SP__SNnom(Writer out, InterchunkWord word1, String blank1, InterchunkWord word2, String blank2, InterchunkWord word3) throws IOException
	{
		if (debug) { logCall("rule17__SVall__SP__SNnom",  word1, blank1,  word2, blank2,  word3); } 
		macro_concord_SN_SV(out, word3, " ", word1);
		macro_simpligo_prs_nbr(out, word1);
		/** 
  <call-macro n="posadet_SN">
    <with-param pos="3"/>
  </call-macro>
 */
		if (word1.tl(attr_a_tns).equals("<inf>"))
		{
			word1.tlSet(attr_a_tns, "<pres>");
		}
		{
			String myword = 
			         word1.tl(attr_whole)
			         ;
			if (myword.length()>0)
			{
				out.append('^');
				out.append(myword);
				out.append('$');
			}
		}
		out.append(blank1);
		{
			String myword = 
			         word2.tl(attr_whole)
			         ;
			if (myword.length()>0)
			{
				out.append('^');
				out.append(myword);
				out.append('$');
			}
		}
		out.append(blank2);
		{
			String myword = 
			         word3.tl(attr_whole)
			         ;
			if (myword.length()>0)
			{
				out.append('^');
				out.append(myword);
				out.append('$');
			}
		}
		/** 
<chunk>
<lit-tag v="REGULO: SV SP SN"/>
<var n="DEBUG"/>
</chunk>
 */
		macro_determina_nbr_gen_SN(out, word3);
	}
	
	/**  fino de postverbaj subjektoj  fino de reguloj serĉantaj ĉefe la subjekton  */
	// REGLA: SV SN - akuzativo
	public void rule18__SVall__SNacc(Writer out, InterchunkWord word1, String blank1, InterchunkWord word2) throws IOException
	{
		if (debug) { logCall("rule18__SVall__SNacc",  word1, blank1,  word2); } 
		macro_simpligo_prs_nbr(out, word1);
		{
			String myword = 
			         word1.tl(attr_whole)
			         ;
			if (myword.length()>0)
			{
				out.append('^');
				out.append(myword);
				out.append('$');
			}
		}
		out.append(blank1);
		out.append('^');
		out.append(word2.tl(attr_lem));
		out.append(word2.tl(attr_a_chunk));
		out.append(word2.tl(attr_prs));
		out.append(word2.tl(attr_gen));
		out.append(word2.tl(attr_nbr));
		out.append("<acc>");
		out.append(word2.tl(attr_chcontent));
		out.append('$');
	}
	
	/**  Ni kolektas tion ĉi en UNU SN en dosiero: antaux_t2x
    <rule comment="REGLA: SN SV SN SNcoo SN">
      <pattern>
        <pattern-item n="SN"/>
        <pattern-item n="SV"/>
        <pattern-item n="SN"/>
        <pattern-item n="SNcoo"/>
        <pattern-item n="SN"/>
      </pattern>
      <action>
        <out>
          <chunk>
            <clip pos="1" part="lem"/>
            <clip pos="1" part="tags"/>
            <clip pos="1" part="chcontent"/>
          </chunk>
    <b pos="1"/>
          <chunk>
            <clip pos="2" part="whole"/>
          </chunk>
    <b pos="2"/>
          <chunk>
            <clip pos="3" part="lem"/>
             <clip pos="3" part="a_chunk"/>
             <clip pos="3" part="gen"/>
             <clip pos="3" part="nbr"/>
           <lit-tag v="acc"/>
            <clip pos="3" part="chcontent"/>
          </chunk>
    <b pos="3"/>
          <chunk>
            <clip pos="4" part="whole"/>
          </chunk>
    <b pos="4"/>
          <chunk>
            <clip pos="5" part="lem"/>
             <clip pos="5" part="a_chunk"/>
             <clip pos="5" part="gen"/>
             <clip pos="5" part="nbr"/>
           <lit-tag v="acc"/>
            <clip pos="5" part="chcontent"/>
          </chunk>
        </out>
      </action>
    </rule>

    <rule comment="REGLA: SN SV SN CC SN SV">
      <pattern>
        <pattern-item n="SN"/>
        <pattern-item n="SV"/>
        <pattern-item n="SN"/>
        <pattern-item n="CC"/>
        <pattern-item n="SN"/>
        <pattern-item n="SV"/>
      </pattern>
      <action>
        <out>
          <chunk>
            <clip pos="1" part="whole"/>
          </chunk>
    <b pos="1"/>
          <chunk>
            <clip pos="2" part="whole"/>
          </chunk>
    <b pos="2"/>
          <chunk>
            <clip pos="3" part="lem"/>
             <clip pos="3" part="a_chunk"/>
             <clip pos="3" part="gen"/>
             <clip pos="3" part="nbr"/>
       <lit-tag v="acc"/>
            <clip pos="3" part="chcontent"/>
          </chunk>
    <b pos="3"/>
          <chunk>
            <clip pos="4" part="whole"/>
          </chunk>
    <b pos="4"/>
          <chunk>
            <clip pos="5" part="lem"/>
             <clip pos="5" part="a_chunk"/>
             <clip pos="5" part="gen"/>
             <clip pos="5" part="nbr"/>
       <lit-tag v="acc"/>
            <clip pos="5" part="chcontent"/>
          </chunk>
    <b pos="5"/>
          <chunk>
            <clip pos="6" part="lem"/>
            <clip pos="6" part="tags"/>
            <clip pos="6" part="chcontent"/>
          </chunk>
        </out>
      </action>
    </rule>
 kiucele?
    <rule comment="REGLA: SN SV SN CC SN Adv - akuzativo">
      <pattern>
        <pattern-item n="SN"/>
        <pattern-item n="SV"/>
        <pattern-item n="SN"/>
        <pattern-item n="CC"/>
        <pattern-item n="SN"/>
        <pattern-item n="Adv"/>
      </pattern>
      <action>
        <out>
          <chunk>
            <clip pos="1" part="whole"/>
          </chunk>
    <b pos="1"/>
          <chunk>
            <clip pos="2" part="whole"/>
          </chunk>
    <b pos="2"/>
          <chunk>
            <clip pos="3" part="lem"/>
             <clip pos="3" part="a_chunk"/>
             <clip pos="3" part="gen"/>
             <clip pos="3" part="nbr"/>
       <lit-tag v="acc"/>
            <clip pos="3" part="chcontent"/>
          </chunk>
    <b pos="3"/>
          <chunk>
            <clip pos="4" part="whole"/>
          </chunk>
    <b pos="4"/>
          <chunk>
            <clip pos="5" part="lem"/>
             <clip pos="5" part="a_chunk"/>
             <clip pos="5" part="gen"/>
             <clip pos="5" part="nbr"/>
       <lit-tag v="acc"/>
            <clip pos="5" part="chcontent"/>
          </chunk>
    <b pos="5"/>
          <chunk>
            <clip pos="6" part="lem"/>
            <clip pos="6" part="tags"/>
            <clip pos="6" part="chcontent"/>
          </chunk>
        </out>
      </action>
    </rule>
 */
	// REGLA: SN Adv SV SN
	public void rule19__SNnom__Adv__SVall__SNacc(Writer out, InterchunkWord word1, String blank1, InterchunkWord word2, String blank2, InterchunkWord word3, String blank3, InterchunkWord word4) throws IOException
	{
		if (debug) { logCall("rule19__SNnom__Adv__SVall__SNacc",  word1, blank1,  word2, blank2,  word3, blank3,  word4); } 
		{
			String myword = 
			         word1.tl(attr_whole)
			         ;
			if (myword.length()>0)
			{
				out.append('^');
				out.append(myword);
				out.append('$');
			}
		}
		out.append(blank1);
		{
			String myword = 
			         word2.tl(attr_whole)
			         ;
			if (myword.length()>0)
			{
				out.append('^');
				out.append(myword);
				out.append('$');
			}
		}
		out.append(blank2);
		{
			String myword = 
			         word3.tl(attr_whole)
			         ;
			if (myword.length()>0)
			{
				out.append('^');
				out.append(myword);
				out.append('$');
			}
		}
		out.append(blank3);
		out.append('^');
		out.append(word4.tl(attr_lem));
		out.append(word4.tl(attr_a_chunk));
		out.append(word4.tl(attr_gen));
		out.append(word4.tl(attr_nbr));
		out.append("<acc>");
		out.append(word4.tl(attr_chcontent));
		out.append('$');
	}
	
	// REGLA: SN Adv SV Adv SN
	public void rule20__SNnom__Adv__SVall__Adv__SNacc(Writer out, InterchunkWord word1, String blank1, InterchunkWord word2, String blank2, InterchunkWord word3, String blank3, InterchunkWord word4, String blank4, InterchunkWord word5) throws IOException
	{
		if (debug) { logCall("rule20__SNnom__Adv__SVall__Adv__SNacc",  word1, blank1,  word2, blank2,  word3, blank3,  word4, blank4,  word5); } 
		{
			String myword = 
			         word1.tl(attr_whole)
			         ;
			if (myword.length()>0)
			{
				out.append('^');
				out.append(myword);
				out.append('$');
			}
		}
		out.append(blank1);
		{
			String myword = 
			         word2.tl(attr_whole)
			         ;
			if (myword.length()>0)
			{
				out.append('^');
				out.append(myword);
				out.append('$');
			}
		}
		out.append(blank2);
		{
			String myword = 
			         word3.tl(attr_whole)
			         ;
			if (myword.length()>0)
			{
				out.append('^');
				out.append(myword);
				out.append('$');
			}
		}
		out.append(blank3);
		{
			String myword = 
			         word4.tl(attr_lem)
			         +word4.tl(attr_tags)
			         +word4.tl(attr_chcontent)
			         ;
			if (myword.length()>0)
			{
				out.append('^');
				out.append(myword);
				out.append('$');
			}
		}
		out.append(blank4);
		out.append('^');
		out.append(word5.tl(attr_lem));
		out.append(word5.tl(attr_a_chunk));
		out.append(word5.tl(attr_gen));
		out.append(word5.tl(attr_nbr));
		out.append("<acc>");
		out.append(word5.tl(attr_chcontent));
		out.append('$');
	}
	
	// REGLA: SV
	public void rule21__SVall(Writer out, InterchunkWord word1) throws IOException
	{
		if (debug) { logCall("rule21__SVall",  word1); } 
		macro_simpligo_prs_nbr(out, word1);
		{
			String myword = 
			         word1.tl(attr_whole)
			         ;
			if (myword.length()>0)
			{
				out.append('^');
				out.append(myword);
				out.append('$');
			}
		}
		out.append("^<REGULO: SV>");
		out.append(var_DEBUG);
		out.append('$');
	}
}
