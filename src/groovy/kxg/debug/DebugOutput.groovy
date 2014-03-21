package kxg.debug

import kxg.searchaf.util.StringUtli;

class DebugOutput {
	def output = new StringBuilder()
	def append(def s){
		output.append('groovy> ').append(s).append('\n')
	}
	def mergeOutput(){
		StringUtli.HtmlEncode(output.toString());
	}
}
