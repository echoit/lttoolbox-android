package org.apertium.transfer.generated;
import java.io.*;
import org.apertium.transfer.*;
import org.apertium.interchunk.InterchunkWord;
public class apertium_eo_en_eo_en_antaux_t2x extends GeneratedTransferBase
{
	public boolean isOutputChunked()
	{
		return false;
	}
	ApertiumRE attr_a_chunk = new ApertiumRE("<S(?:N|V)>");
	ApertiumRE attr_a_nbr = new ApertiumRE("<(?:sg|pl|sp|ND)>");
	ApertiumRE attr_a_cas = new ApertiumRE("<(?:nom|acc)>");
	ApertiumRE attr_a_prs = new ApertiumRE("<p(?:1|2|3)>");
	ApertiumRE attr_a_gender = new ApertiumRE("<(?:nt|mf|GD|m|f)>");
	ApertiumRE attr_lem = new ApertiumRE("(([^<]|\"\\<\")+)");
	ApertiumRE attr_lemq = new ApertiumRE("\\#[- _][^<]+");
	ApertiumRE attr_lemh = new ApertiumRE("(([^<#]|\"\\<\"|\"\\#\")+)");
	ApertiumRE attr_whole = new ApertiumRE("(.+)");
	ApertiumRE attr_tags = new ApertiumRE("((<[^>]+>)+)");
	ApertiumRE attr_chname = new ApertiumRE("(\\{([^/]+)\\/)");
	ApertiumRE attr_chcontent = new ApertiumRE("(\\{.+)");
	ApertiumRE attr_content = new ApertiumRE("(\\{.+)");
	String var_nombre = "";
	String var_genere = "";
	
	/** 
    <rule comment="REGLA: NUM SN   -   SN ">
      <pattern>
        <pattern-item n="num"/>
        <pattern-item n="SN"/>
      </pattern>
      <action>
        <out>
          <chunk>
            <get-case-from pos="1"><clip pos="2" part="lem"/></get-case-from>
            <clip pos="2" part="tags"/>
            <lit v="{"/>
            <clip pos="1" part="content"/>
                <b pos="1"/>
            <clip pos="2" part="content"/>
             <lit v="}"/>
          </chunk>
        </out>
      </action>
    </rule>
 tiu regulo ne veras: se SN komenciĝas per DET la antaŭa SA ne apartenas al la koncerna SN

    <rule comment="REGLA: SA SN   -   SN ">
      <pattern>
        <pattern-item n="SA"/>
        <pattern-item n="SN"/>
      </pattern>
      <action>
        <out>
          <chunk>
            <get-case-from pos="1"><clip pos="2" part="lem"/></get-case-from>
            <clip pos="2" part="tags"/>
            <lit v="{"/>
            <clip pos="1" part="content"/>
                <b pos="1"/>
            <clip pos="2" part="content"/>
             <lit v="}"/>
          </chunk>
        </out>
      </action>
    </rule>


Ne funkcias kiam la dua SN ne havas la saman nombron ol la dua SN (ekz. ĉar SN1 estas unkown)

    <rule comment="REGLA: SN DE SN  -   SN ">
      <pattern>
        <pattern-item n="SN"/>
        <pattern-item n="de"/>
        <pattern-item n="SN"/>
      </pattern>
      <action>
        <out>
          <chunk>
            <clip pos="1" part="lem"/>
            <clip pos="1" part="tags"/>
            <lit v="{"/>
            <clip pos="1" part="content"/>
                <b pos="1"/>
            <clip pos="2" part="content"/>
                <b pos="2"/>
            <clip pos="3" part="content"/>
             <lit v="}"/>
          </chunk>
        </out>
      </action>
    </rule>
 */
	// REGLA: PREP SN_SD   -   SP (sintagmo prepozicia)
	public void rule0__PREP__SN_SD(Writer out, InterchunkWord word1, String blank1, InterchunkWord word2) throws IOException
	{
		if (debug) { logCall("rule0__PREP__SN_SD",  word1, blank1,  word2); } 
		out.append('^');
		out.append(TransferWord.copycase(word1.sl(attr_lem), "sp"));
		out.append("<SP>");
		out.append(word2.tl(attr_a_prs));
		out.append(word2.tl(attr_a_gender));
		out.append(word2.tl(attr_a_nbr));
		out.append(word2.tl(attr_a_cas));
		out.append("{");
		out.append(TransferWord.stripBrackets(word1.tl(attr_content)));
		out.append(blank1);
		out.append(TransferWord.stripBrackets(word2.tl(attr_content)));
		out.append("}$");
	}
}
