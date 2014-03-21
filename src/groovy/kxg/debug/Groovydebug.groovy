package kxg.debug;

import java.util.Map;

import kxg.searchaf.url.SearchTaskFactory;
import kxg.searchaf.util.StringUtli;
import groovy.ui.SystemOutputInterceptor
import groovy.lang.Binding;
import groovy.lang.GroovyShell;

public class Groovydebug {



	/**
	 * For use by the web-based console. The result is a Map, with output (including stdout if
	 * specified under the 'output' key, the execution result under the 'result' key,
	 * and any exception under the 'exception' key
	 *
	 * @param code  Groovy code to execute
	 * @param captureStdout  if <code>true</code>, redirects stdout during execution
	 * @return the output, result, and exception
	 */
	Map eval(String code ) { 
		//		println code
		code=StringUtli.HtmlDiscode(code);
		//		println code
		code=code.replaceAll("println", "_kxgGroovyDebug_output.append  ")
		//		code=code+"; return _kxgGroovyDebug_output";

		def map = [:]
		def output = new DebugOutput()
		output.append("result is:")
		//		def systemOutInterceptor = new SystemOutputInterceptor({ String s ->
		//			output.append s
		//			return false
		//		})
		//		systemOutInterceptor.start()

		try {
			Binding binding = new Binding();
			binding.setVariable("_kxgGroovyDebug_output",output);
			binding.setVariable("searchTask",SearchTaskFactory.getInstance());
			GroovyShell shell = new GroovyShell(binding);
			Object value =shell.evaluate(code)

			map.result = value
		}
		catch (Throwable t) {
			map.exception = StringUtli.HtmlEncode t.toString()
			//println t
		}
		finally {
			//			systemOutInterceptor?.stop()

		}

		map.output =output.mergeOutput();
		map
	}


	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Groovydebug debug=new Groovydebug();
		Map result= debug.eval("def af=searchTask.af; println af.allmenoldprolist.size();");
		println result.output
		println result.exception

	}
}
