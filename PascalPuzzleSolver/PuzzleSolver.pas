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
    solution: array of PuzzleSolver_kref_com_emc_symmwin_puzzle_Solver_Move;
  public
    constructor Create;
    destructor Destroy; override;
    function getValue(row: Integer; column: Integer): Integer;
    function tryMove(row: Integer; column: Integer): Boolean;
    function getSolutionSize: Integer;
    function trySolution: Boolean;
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
  board := symbols.kotlin.root.com.emc.symmwin.puzzle.Solver.Board.Companion.getBoard(boardCompanion,SIZE,12);
end;

function TPuzzle.getValue(row: Integer; column: Integer): Integer;
begin
  Result := symbols.kotlin.root.com.emc.symmwin.puzzle.Solver.Board.number(board, row, column);
end;

function TPuzzle.tryMove(row: Integer; column: Integer): Boolean;
begin
  Result := symbols.kotlin.root.com.emc.symmwin.puzzle.Solver.Board.tryMove(board, row, column) <> 0;
end;

function TPuzzle.getSolutionSize: Integer;
var
  move: PuzzleSolver_kref_com_emc_symmwin_puzzle_Solver_Move;
  size: Integer;
begin
  for move in solution do
    if move.pinned <> nil then
      symbols.DisposeStablePointer(move.pinned);
  size := 0;
  SetLength(solution,size);
  move := symbols.kotlin.root.com.emc.symmwin.puzzle.Solver.solve(solver,board,MAX_QUEUE_SIZE);
  while move.pinned <> nil do
    begin
      SetLength(solution, size + 1);
      solution[size] := move;
      move := symbols.kotlin.root.com.emc.symmwin.puzzle.Solver.Move.get_parent(move);
      Inc(size);
    end;
  Result := Length(Solution);
end;

function TPuzzle.trySolution: Boolean;
var
  i: Integer;
begin
  i := High(solution);
  Result := i >= 0;
  if Result then
    begin
      symbols.kotlin.root.com.emc.symmwin.puzzle.Solver.Board.makeMove(board,solution[i]);
      SetLength(solution,i);
    end
end;

function TPuzzle.distanceFromSolution: Integer;
begin
  Result := symbols.kotlin.root.com.emc.symmwin.puzzle.Solver.Board.distanceFromSolution(board);
end;

end.
