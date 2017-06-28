Usage:

The project is compiled with Makefile. Simply type in command line
"make" when in the directory that contains the file "Makefile".

The stepper is executed from the command line this way:
"java bgames.Bgames <input_file>". The input file should contain
the source code, examples are listed in the directory "tests".

The stepper executes the source code step by step, and prints the
current state of execution into the console. The stepper waits for
the human to press "Enter" to advance and perform one step of execution.

------------------------------------------------------------------------

The point of the stepper: In the target project (where we would like
to implement board games), we came up with the following idea:
We can look at board games as "multiparty computation". There is the
main program, which most of the time doesn't require any user input.
From time to time, it requests a value (or some other interaction) from
one of the players. All players know most of the state of the execution.

This stepper is a first attempt at visualizing the state of execution.
(There are no players yet.)
