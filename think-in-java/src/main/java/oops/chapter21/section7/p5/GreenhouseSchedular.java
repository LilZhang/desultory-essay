/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanTao Information Co., Ltd.
 * All rights reserved.
 *
 */

package oops.chapter21.section7.p5;

import java.util.*;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Description:
 * <p/>
 * Create Author  : lilzhang
 * Create Date    : 2016-09-06
 * Project        : desultory-essay
 * File Name      : GreenhouseSchedular.java
 */
public class GreenhouseSchedular
{
    private volatile boolean light = false;

    private volatile boolean water = false;

    private String thermostat = "Day";

    ScheduledThreadPoolExecutor scheduledThreadPoolExecutor =
            new ScheduledThreadPoolExecutor(10);

    public synchronized String getThermostat()
    {
        return thermostat;
    }

    public synchronized void setThermostat(String thermostat)
    {
        this.thermostat = thermostat;
    }

    public void schedule(Runnable event, long delay)
    {
        scheduledThreadPoolExecutor.schedule(event, delay, TimeUnit.MILLISECONDS);
    }

    public void repeat(Runnable event, long initialDelay, long period)
    {
        scheduledThreadPoolExecutor.scheduleAtFixedRate(
                event, initialDelay, period, TimeUnit.MILLISECONDS);
    }

    // inner classes start
    class LightOn implements Runnable
    {
        public void run()
        {
            System.out.println("Turning on lights");
            light = true;
        }
    }

    class LightOff implements Runnable
    {
        public void run()
        {
            System.out.println("Turning off lights");
        }
    }

    class WaterOn implements Runnable
    {
        public void run()
        {
            System.out.println("Turning water on");
            water = true;
        }
    }

    class WaterOff implements Runnable
    {
        public void run()
        {
            System.out.println("Turning water off");
            water = false;
        }
    }

    class ThermostatNight implements Runnable
    {
        public void run()
        {
            System.out.println("Thermostat to night setting");
            setThermostat("Night");
        }
    }

    class ThermostatDay implements Runnable
    {
        public void run()
        {
            System.out.println("Thermostat to day setting");
            setThermostat("Day");
        }
    }

    class Bell implements Runnable
    {
        public void run()
        {
            System.out.println("Bing!");
        }
    }

    class Terminate implements Runnable
    {
        public void run()
        {
            System.out.println("Terminating");
            scheduledThreadPoolExecutor.shutdownNow();
            new Thread()
            {
                @Override
                public void run()
                {
                    for (DataPoint point : data)
                    {
                        System.out.println(point);
                    }
                }
            }.start();
        }
    }
    // inner classes end

    static class DataPoint
    {
        final Calendar time;

        final float temperature;

        final float humidity;

        public DataPoint(Calendar time, float temperature, float humidity)
        {
            this.time = time;
            this.temperature = temperature;
            this.humidity = humidity;
        }

        @Override
        public String toString()
        {
            final StringBuilder sb = new StringBuilder("DataPoint{");
            sb.append("time=").append(time);
            sb.append(", temperature=").append(temperature);
            sb.append(", humidity=").append(humidity);
            sb.append('}');
            return sb.toString();
        }
    }

    private Calendar lastTime = Calendar.getInstance();
    {
        lastTime.set(Calendar.MINUTE, 30);
        lastTime.set(Calendar.SECOND, 0);
    }

    private float lastTemp = 65.0f;

    private int tempDirection = 1;

    private float lastHumidity = 65.0f;

    private int humidityDirection = 1;

    private Random random = new Random();

    List<DataPoint> data = Collections.synchronizedList(new ArrayList<DataPoint>());

    class CollectData implements Runnable
    {
        public void run()
        {
            System.out.println("Collecting data");
            synchronized (GreenhouseSchedular.this)
            {
                lastTime.set(Calendar.MINUTE,
                        lastTime.get(Calendar.MINUTE) + 30);

                if (random.nextInt(5) == 4)
                {
                    tempDirection = -tempDirection;
                }
                lastTemp = lastTemp + tempDirection * (1.0f + random.nextFloat());
                if (random.nextInt(5) == 4)
                {
                    humidityDirection = -humidityDirection;
                }
                lastHumidity = lastHumidity + humidityDirection * random.nextFloat();
                data.add(new DataPoint(((Calendar) lastTime.clone()), lastTemp, lastHumidity));
            }
        }
    }

    public static void main(String[] args)
    {
        GreenhouseSchedular gh = new GreenhouseSchedular();
        gh.schedule(gh.new Terminate(), 5000);

        gh.repeat(gh.new Bell(), 0, 1000);
        gh.repeat(gh.new ThermostatNight(), 0, 2000);
        gh.repeat(gh.new LightOn(), 0, 200);
        gh.repeat(gh.new LightOff(), 0, 400);
        gh.repeat(gh.new WaterOn(), 0, 600);
        gh.repeat(gh.new WaterOff(), 0, 800);
        gh.repeat(gh.new ThermostatDay(), 0, 1400);
        gh.repeat(gh.new CollectData(), 500, 500);
    }
}
