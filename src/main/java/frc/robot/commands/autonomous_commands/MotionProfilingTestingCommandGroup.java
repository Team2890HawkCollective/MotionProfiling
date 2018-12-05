package frc.robot.commands.autonomous_commands;

import frc.team319.commands.*;
import frc.robot.paths.*;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class MotionProfilingTestingCommandGroup extends CommandGroup
{
	public MotionProfilingTestingCommandGroup()
	{
		System.out.println("Hewwo mr obawma?");
		addSequential(new FollowTrajectory(new turnLeft()));
	}
}