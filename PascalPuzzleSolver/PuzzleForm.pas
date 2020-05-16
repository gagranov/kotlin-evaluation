{$IFDEF FPC}
{$MODE DELPHIUNICODE}
{$ENDIF}

unit PuzzleForm;

interface

uses
  PuzzleSolver,
  Classes,
  SysUtils,
  Forms,
  Controls,
  Graphics,
  StdCtrls,
  Menus,
  Dialogs,
  ExtCtrls;

type

  TForm1 = class(TForm)
    Again: TMainMenu;
    MainMenu1: TMainMenu;
    Panel1: TPanel;
    Solve: TMenuItem;
    procedure OnFormCreate(Sender: TObject);
    procedure OnFormDestroy(Sender: TObject);
    procedure OnFormResize(Sender: TObject);
    procedure OnSolve(Sender: TObject);
    procedure OnStartOver(Sender: TObject);
  private
    puzzle: TPuzzle;
    buttons: array[0..SIZE-1] of array[0..SIZE-1] of TButton;
    procedure rebuild;
    procedure OnButtonClick(Sender: TObject);
  end;

var
  Form1: TForm1;

implementation

{$R *.lfm}

procedure TForm1.OnFormCreate(Sender: TObject);
var
  r,c: Integer;
begin
  puzzle := TPuzzle.Create;
  for r := 0 to SIZE - 1 do
    for c := 0 to SIZE - 1 do
      begin
        buttons[r][c] := TButton.Create(Self);
        with buttons[r][c] do
        begin
          Parent := Panel1;
          Tag := r * SIZE + c;
          OnClick := OnButtonClick;
        end;
      end;
end;

procedure TForm1.OnFormDestroy(Sender: TObject);
begin
  FreeAndNil(puzzle);
end;

procedure TForm1.rebuild;
var
  r,c,v: Integer;
begin
  for r := 0 to SIZE - 1 do
    for c := 0 to SIZE - 1 do
      begin
        v := puzzle.getValue(r,c);
        buttons[r][c].Caption := Format('%d',[v]);
        buttons[r][c].Visible := v <> 0;
      end;
end;

procedure TForm1.OnFormResize(Sender: TObject);
const
  GAP = 4;
var
  r,c,buttonWidth,buttonHeight: integer;
begin
  buttonWidth := (Panel1.Width - GAP * (SIZE + 1)) div SIZE;
  buttonHeight := (Panel1.Height - GAP * (SIZE + 1)) div SIZE;
  for r := 0 to SIZE - 1 do
    for c := 0 to SIZE - 1 do
      with buttons[r][c] do
      begin
        Width := buttonWidth;
        Height := buttonHeight;
        Top := GAP + r * (buttonHeight + GAP);
        Left := GAP + c * (buttonWidth + GAP);
      end;
  rebuild;
end;

procedure TForm1.OnSolve(Sender: TObject);
begin
  puzzle.getSolutionSize;
  while puzzle.trySolution do
    begin
      Sleep(1000);
      rebuild;
      Repaint;
    end;
  if puzzle.distanceFromSolution = 0 then
    showMessage('Solved!')
  else
    showMessage('Sorry, could not solve!')
end;

procedure TForm1.OnStartOver(Sender: TObject);
begin
  puzzle.createBoard;
  rebuild;
end;

procedure TForm1.OnButtonClick(Sender: TObject);
var
  r,c: integer;
begin
  if Sender is TButton then
    begin
      r := TButton(Sender).TAG div SIZE;
      c := TButton(Sender).TAG mod SIZE;
      if puzzle.tryMove(r,c) then
        rebuild
    end
end;

end.

