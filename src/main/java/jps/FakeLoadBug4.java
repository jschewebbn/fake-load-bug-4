package jps;

import java.util.concurrent.TimeUnit;

import com.martensigwart.fakeload.FakeLoad;
import com.martensigwart.fakeload.FakeLoadExecutor;
import com.martensigwart.fakeload.FakeLoadExecutors;
import com.martensigwart.fakeload.FakeLoads;
import com.martensigwart.fakeload.MemoryUnit;


public class FakeLoadBug4 {
  public static void main(final String[] args) {
    generateNodeLoad(0.75, 60 * 1000);
  }


  private static boolean generateNodeLoad(final double cpu, final long duration) {
    final int cpuIntPercent = (int) Math.round(cpu * 100);

    System.out.println(String.format("Generating node load with CPU of %d%% for a duration of %dms.", cpuIntPercent, duration));
    final FakeLoad load = FakeLoads.create().withCpu(cpuIntPercent)
      .lasting(duration, TimeUnit.MILLISECONDS);

    try {
      final FakeLoadExecutor executor = FakeLoadExecutors.newDefaultExecutor();
      executor.execute(load);
      System.out.println("Load generation complete.");
    } catch (RuntimeException e) {
      System.out.println("Load generation failed: " + e.getMessage());
      return false;
    }

    return true;
  }  
}
