package frc.robot.commands.autonomous_commands;

import frc.team319.commands.*;
import frc.robot.paths.*;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class MotionProfilingTestingCommandGroup extends CommandGroup
{
	public MotionProfilingTestingCommandGroup()
	{
		addSequential(new FollowTrajectory(new turnLeft()));
	}
}