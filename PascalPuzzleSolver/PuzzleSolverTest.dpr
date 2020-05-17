program PuzzleSolverTest;

{$APPTYPE CONSOLE}

uses
  KotlinPuzzleSolver,
  PuzzleSolver,
  SysUtils;

var
  symbols: PuzzleSolver_ExportedSymbols;
  solver: PuzzleSolver_kref_com_emc_symmwin_puzzle_Solver;
  boardCompanion: PuzzleSolver_kref_com_emc_symmwin_puzzle_Solver_Board_Companion;

  procedure printBoard(const text: string;
                       board: PuzzleSolver_kref_com_emc_symmwin_puzzle_Solver_Board);
  var
    row,column,value: Integer;
  begin
    WriteLn(Format('%s: Distance %d:',[text,symbols.kotlin.root.com.emc.symmwin.puzzle.Solver.Board.distanceFromSolution(board)]));
    for row := 1 to SIZE do
      begin
        for column := 1 to SIZE do
          begin
            value := symbols.kotlin.root.com.emc.symmwin.puzzle.Solver.Board.number(board, row - 1, column - 1);
            if value = 0 then
              Write('   ')
            else
              Write(Format(' %2d',[value]))
          end;
        WriteLn
      end;
    WriteLn
  end;

  procedure printSolution(board: PuzzleSolver_kref_com_emc_symmwin_puzzle_Solver_Board;
                          var count: Integer);
  var
    parent: PuzzleSolver_kref_com_emc_symmwin_puzzle_Solver_Board;
  begin
    parent := symbols.kotlin.root.com.emc.symmwin.puzzle.Solver.Board.get_parent(board);
    if parent.pinned <> nil then
      begin
        printSolution(parent, count);
        Inc(count);
      end;
    printBoard(Format('Step %d',[count]),board);
  end;

var
  puzzle: PuzzleSolver_kref_com_emc_symmwin_puzzle_Solver_Board;
  solution: PuzzleSolver_kref_com_emc_symmwin_puzzle_Solver_Board;
  count: Integer;
begin
  try
    symbols := PuzzleSolver_symbols^;
    solver := symbols.kotlin.root.com.emc.symmwin.puzzle.Solver._instance;
    boardCompanion := symbols.kotlin.root.com.emc.symmwin.puzzle.Solver.Board.Companion._instance;
    puzzle := symbols.kotlin.root.com.emc.symmwin.puzzle.Solver.Board.Companion.getBoard(boardCompanion,SIZE,9);
    printBoard('Puzzle', puzzle);
    solution := symbols.kotlin.root.com.emc.symmwin.puzzle.Solver.solve(solver,puzzle,MAX_QUEUE_SIZE);
    count := 1;
    printSolution(solution, count);
  except
    on E: Exception do
      write(E.Message);
  end
end.
