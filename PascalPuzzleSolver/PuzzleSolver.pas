{$IFDEF FPC}
{$MODE DELPHIUNICODE}
{$ENDIF}

unit PuzzleSolver;

interface

uses
  KotlinPuzzleSolver;

const
  SIZE = 4;
  MAX_QUEUE_SIZE = 1000000;

type
  TPuzzle = class
  private
    symbols: PuzzleSolver_ExportedSymbols;
    solver: PuzzleSolver_kref_com_emc_symmwin_puzzle_Solver;
    boardCompanion: PuzzleSolver_kref_com_emc_symmwin_puzzle_Solver_Board_Companion;
    board: PuzzleSolver_kref_com_emc_symmwin_puzzle_Solver_Board;
    solution: array of PuzzleSolver_kref_com_emc_symmwin_puzzle_Solver_Board;
  public
    constructor Create;
    destructor Destroy; override;
    function getValue(row: Integer; column: Integer): Integer;
    function tryMove(row: Integer; column: Integer): Boolean;
    procedure getSolution;
    function showSolution: Boolean;
    procedure createBoard;
    function distanceFromSolution: Integer;
  end;

implementation

constructor TPuzzle.Create;
begin
  inherited;
  symbols := PuzzleSolver_symbols^;
  solver := symbols.kotlin.root.com.emc.symmwin.puzzle.Solver._instance;
  boardCompanion := symbols.kotlin.root.com.emc.symmwin.puzzle.Solver.Board.Companion._instance;
  board.pinned := nil;
  solution := nil;
  createBoard;
end;

destructor TPuzzle.Destroy;
begin
  inherited;
end;

procedure TPuzzle.createBoard;
begin
  if board.pinned <> nil then
    symbols.DisposeStablePointer(board.pinned);
  board := symbols.kotlin.root.com.emc.symmwin.puzzle.Solver.Board.Companion.getBoard(boardCompanion,SIZE,9);
end;

function TPuzzle.getValue(row: Integer; column: Integer): Integer;
begin
  Result := symbols.kotlin.root.com.emc.symmwin.puzzle.Solver.Board.number(board, row, column);
end;

function TPuzzle.tryMove(row: Integer; column: Integer): Boolean;
begin
  Result := symbols.kotlin.root.com.emc.symmwin.puzzle.Solver.Board.tryMove(board, row, column) <> 0;
end;

procedure TPuzzle.getSolution;
var
  solutionBoard: PuzzleSolver_kref_com_emc_symmwin_puzzle_Solver_Board;
  size: Integer;
begin
  for solutionBoard in solution do
    if solutionBoard.pinned <> nil then
      symbols.DisposeStablePointer(solutionBoard.pinned);
  size := 0;
  SetLength(solution,size);
  solutionBoard := symbols.kotlin.root.com.emc.symmwin.puzzle.Solver.solve(solver,board,MAX_QUEUE_SIZE);
  while solutionBoard.pinned <> nil do
    begin
      SetLength(solution, size + 1);
      solution[size] := solutionBoard;
      solutionBoard := symbols.kotlin.root.com.emc.symmwin.puzzle.Solver.Board.get_parent(solutionBoard);
      Inc(size);
    end;
end;

function TPuzzle.showSolution: Boolean;
var
  i: Integer;
begin
  i := High(solution);
  Result := i >= 0;
  if Result then
    begin
      if board.pinned <> nil then
        symbols.DisposeStablePointer(board.pinned);
      board := solution[i];
      SetLength(solution,i);
    end
end;

function TPuzzle.distanceFromSolution: Integer;
begin
  Result := symbols.kotlin.root.com.emc.symmwin.puzzle.Solver.Board.distanceFromSolution(board);
end;

end.
