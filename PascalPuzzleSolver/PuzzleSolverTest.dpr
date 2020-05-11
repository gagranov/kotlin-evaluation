program PuzzleSolverTest;

{$APPTYPE CONSOLE}

uses
  PuzzleSolver,
  SysUtils;

const SIZE = 5;

var
  row,column: Integer;
  boardCompanion: PuzzleSolver_kref_com_emc_symmwin_puzzle_Solver_Board_Companion;
  board: PuzzleSolver_kref_com_emc_symmwin_puzzle_Solver_Board;

begin
  try
    //PuzzleSolver_symbols.kotlin.root.com.emc.symmwin.puzzle.main();
    boardCompanion := PuzzleSolver_symbols.kotlin.root.com.emc.symmwin.puzzle.Solver.Board.Companion._instance;
    board := PuzzleSolver_symbols.kotlin.root.com.emc.symmwin.puzzle.Solver.Board.Companion.getBoard(boardCompanion,SIZE,100);
    for row := 1 to SIZE do
      for column := 1 to SIZE do
        Writeln(Format('[%d,%d] = %d',[row-1,
                                       column-1,
                                       PuzzleSolver_symbols.kotlin.root.com.emc.symmwin.puzzle.Solver.Board.number(board, row -1, column -1)]));
  except
    on E: Exception do
      write(E.Message);
  end
end.
