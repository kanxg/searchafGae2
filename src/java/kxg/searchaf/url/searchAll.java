package kxg.searchaf.url;

import java.util.Date;
import java.util.Properties;
import java.util.Timer;

import kxg.searchaf.util.ProxyUtli;

public class searchAll {

	// private static Scheduler sched = null;;

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {

		ProxyUtli.setProxy(true);
		SearchTask task = new SearchTask();
		task.run();

		// Timer timer = new Timer();
		// timer.schedule(new SearchTask(), 1000L,
		// 1000L * 60L * Constant.sleeptime);//
		// 在1秒后执行此任务,每次间隔2秒,如果传递一个Data参数,就可以在某个固定的时间执行这个任务.

		// Timer timer = new Timer();
		// timer.schedule(new AfTask(), 1000L, 1000L * 60L *
		// AfConstant.sleeptime);//
		// 在1秒后执行此任务,每次间隔2秒,如果传递一个Data参数,就可以在某个固定的时间执行这个任务.

		// timer = new Timer();
		// timer.schedule(new HollisterTask(), 1000L,
		// 1000L * 60L * HollistercoConstant.sleeptime);//
		// 在1秒后执行此任务,每次间隔2秒,如果传递一个Data参数,就可以在某个固定的时间执行这个任务.

		// timer = new Timer();
		// timer.schedule(new GymboreeTask(), 1000L,
		// 1000L * 60L * GymboreeConstant.sleeptime);//
		// 在1秒后执行此任务,每次间隔2秒,如果传递一个Data参数,就可以在某个固定的时间执行这个任务.

		// timer = new Timer();
		// timer.schedule(new RalphlaurenTask(), 1000L,
		// 1000L * 60L * RalphlaurenConstant.sleeptime);//
		// 在1秒后执行此任务,每次间隔2秒,如果传递一个Data参数,就可以在某个固定的时间执行这个任务.

		// timer = new Timer();
		// timer.schedule(new TommyTask(), 1000L,
		// 1000L * 60L * TommyConstant.sleeptime);//
		// 在1秒后执行此任务,每次间隔2秒,如果传递一个Data参数,就可以在某个固定的时间执行这个任务.

		// timer = new Timer();
		// timer.schedule(new NeimanTask(), 1000L,
		// 1000L * 60L * NeimanConstant.sleeptime);//
		// 在1秒后执行此任务,每次间隔2秒,如果传递一个Data参数,就可以在某个固定的时间执行这个任务.
		//
		// timer = new Timer();
		// timer.schedule(new SaksTask(), 1000L,
		// 1000L * 60L * SaksConstant.sleeptime);//
		// 在1秒后执行此任务,每次间隔2秒,如果传递一个Data参数,就可以在某个固定的时间执行这个任务.
		//
		// timer = new Timer();
		// timer.schedule(new SephoraTask(), 1000L,
		// 1000L * 60L * SephoraConstant.sleeptime);//
		// 在1秒后执行此任务,每次间隔2秒,如果传递一个Data参数,就可以在某个固定的时间执行这个任务.

		// timer = new Timer();
		// timer.schedule(new AptamilTask(), 1000L,
		// 1000L * 60L * AptamilConstant.sleeptime);//
		// 在1秒后执行此任务,每次间隔2秒,如果传递一个Data参数,就可以在某个固定的时间执行这个任务.

	}

}
