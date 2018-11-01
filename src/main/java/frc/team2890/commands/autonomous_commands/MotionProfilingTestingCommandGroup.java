package frc.team2890.commands.autonomous_commands;

import frc.team319.commands.*;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class MotionProfilingTestingCommandGroup extends CommandGroup
{
	public MotionProfilingTestingCommandGroup()
	{
		addSequential(new FollowTrajectory("turnLeft"));
	}
}