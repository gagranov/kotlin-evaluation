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

  procedure printMove(board: PuzzleSolver_kref_com_emc_symmwin_puzzle_Solver_Board;
                      move: PuzzleSolver_kref_com_emc_symmwin_puzzle_Solver_Move;
                      var count: Integer);
  var
    row,column: Integer;
    parent: PuzzleSolver_kref_com_emc_symmwin_puzzle_Solver_Move;
  begin
    row := symbols.kotlin.root.com.emc.symmwin.puzzle.Solver.Move.get_row(move);
    column := symbols.kotlin.root.com.emc.symmwin.puzzle.Solver.Move.get_column(move);
    parent := symbols.kotlin.root.com.emc.symmwin.puzzle.Solver.Move.get_parent(move);
    if parent.pinned <> nil then
      begin
        printMove(board, parent, count);
        Inc(count);
      end;
    symbols.kotlin.root.com.emc.symmwin.puzzle.Solver.Board.makeMove(board,move);
    printBoard(Format('Move %d: "blank" to row=%d column=%d',[count, row + 1, column + 1]),board);
  end;

var
  puzzle: PuzzleSolver_kref_com_emc_symmwin_puzzle_Solver_Board;
  move: PuzzleSolver_kref_com_emc_symmwin_puzzle_Solver_Move;
  count: Integer;
begin
  try
    symbols := PuzzleSolver_symbols^;
    solver := symbols.kotlin.root.com.emc.symmwin.puzzle.Solver._instance;
    boardCompanion := symbols.kotlin.root.com.emc.symmwin.puzzle.Solver.Board.Companion._instance;
    puzzle := symbols.kotlin.root.com.emc.symmwin.puzzle.Solver.Board.Companion.getBoard(boardCompanion,SIZE,16);
    printBoard('Puzzle', puzzle);
    move := symbols.kotlin.root.com.emc.symmwin.puzzle.Solver.solve(solver,puzzle,MAX_QUEUE_SIZE);
    count := 1;
    printMove(puzzle,move,count);
  except
    on E: Exception do
      write(E.Message);
  end
end.
