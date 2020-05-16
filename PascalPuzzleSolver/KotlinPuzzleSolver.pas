unit KotlinPuzzleSolver;

{$IFDEF FPC}
{$MODE DELPHIUNICODE}
{$PACKRECORDS C}
{$ENDIF}

interface

{
  Automatically converted by H2Pas 1.0.0 from PuzzleSolver_api.h
  The following command line parameters were used:
    -D
    -P
    -o
    KotlinPuzzleSolver.pas
    -l
    PuzzleSolver
    PuzzleSolver_api.h
}

type
  Pchar  = ^char;
  PuzzleSolver_KType = record
  end;
  PPuzzleSolver_KType  = ^PuzzleSolver_KType;

  type
    PuzzleSolver_KBoolean = byte;

    PuzzleSolver_KChar = word;

    PuzzleSolver_KByte = char;

    PuzzleSolver_KShort = smallint;

    PuzzleSolver_KInt = longint;

    PuzzleSolver_KLong = int64;

    PuzzleSolver_KUByte = byte;

    PuzzleSolver_KUShort = word;

    PuzzleSolver_KUInt = longword;

    PuzzleSolver_KULong = uint64;

    PuzzleSolver_KFloat = single;

    PuzzleSolver_KDouble = double;

    PuzzleSolver_KNativePtr = pointer;

    PuzzleSolver_kref_kotlin_Byte = record
        pinned : PuzzleSolver_KNativePtr;
      end;

    PuzzleSolver_kref_kotlin_Short = record
        pinned : PuzzleSolver_KNativePtr;
      end;

    PuzzleSolver_kref_kotlin_Int = record
        pinned : PuzzleSolver_KNativePtr;
      end;

    PuzzleSolver_kref_kotlin_Long = record
        pinned : PuzzleSolver_KNativePtr;
      end;

    PuzzleSolver_kref_kotlin_Float = record
        pinned : PuzzleSolver_KNativePtr;
      end;

    PuzzleSolver_kref_kotlin_Double = record
        pinned : PuzzleSolver_KNativePtr;
      end;

    PuzzleSolver_kref_kotlin_Char = record
        pinned : PuzzleSolver_KNativePtr;
      end;

    PuzzleSolver_kref_kotlin_Boolean = record
        pinned : PuzzleSolver_KNativePtr;
      end;

    PuzzleSolver_kref_kotlin_Unit = record
        pinned : PuzzleSolver_KNativePtr;
      end;

    PuzzleSolver_kref_kotlin_Function0 = record
        pinned : PuzzleSolver_KNativePtr;
      end;

    PuzzleSolver_kref_com_emc_symmwin_puzzle_TestResults = record
        pinned : PuzzleSolver_KNativePtr;
      end;

    PuzzleSolver_kref_com_emc_symmwin_puzzle_Solver = record
        pinned : PuzzleSolver_KNativePtr;
      end;

    PuzzleSolver_kref_com_emc_symmwin_puzzle_Solver_Board = record
        pinned : PuzzleSolver_KNativePtr;
      end;

    PuzzleSolver_kref_com_emc_symmwin_puzzle_Solver_Move = record
        pinned : PuzzleSolver_KNativePtr;
      end;

    PuzzleSolver_kref_kotlin_collections_List = record
        pinned : PuzzleSolver_KNativePtr;
      end;

    PuzzleSolver_kref_com_emc_symmwin_puzzle_Solver_Board_Companion = record
        pinned : PuzzleSolver_KNativePtr;
      end;

    PuzzleSolver_ExportedSymbols = record
        DisposeStablePointer : procedure (ptr:PuzzleSolver_KNativePtr);cdecl;
        DisposeString : procedure (_string:Pchar);cdecl;
        IsInstance : function (ref:PuzzleSolver_KNativePtr; _type:PPuzzleSolver_KType):PuzzleSolver_KBoolean;cdecl;
        createNullableByte : function (_para1:PuzzleSolver_KByte):PuzzleSolver_kref_kotlin_Byte;cdecl;
        createNullableShort : function (_para1:PuzzleSolver_KShort):PuzzleSolver_kref_kotlin_Short;cdecl;
        createNullableInt : function (_para1:PuzzleSolver_KInt):PuzzleSolver_kref_kotlin_Int;cdecl;
        createNullableLong : function (_para1:PuzzleSolver_KLong):PuzzleSolver_kref_kotlin_Long;cdecl;
        createNullableFloat : function (_para1:PuzzleSolver_KFloat):PuzzleSolver_kref_kotlin_Float;cdecl;
        createNullableDouble : function (_para1:PuzzleSolver_KDouble):PuzzleSolver_kref_kotlin_Double;cdecl;
        createNullableChar : function (_para1:PuzzleSolver_KChar):PuzzleSolver_kref_kotlin_Char;cdecl;
        createNullableBoolean : function (_para1:PuzzleSolver_KBoolean):PuzzleSolver_kref_kotlin_Boolean;cdecl;
        createNullableUnit : function :PuzzleSolver_kref_kotlin_Unit;cdecl;
        kotlin : record
            root : record
                com : record
                    emc : record
                        symmwin : record
                            puzzle : record
                                getSystemTimeInMillis : function :PuzzleSolver_KLong;cdecl;
                                main : procedure ;cdecl;
                                measureTimeMillis : function (block:PuzzleSolver_kref_kotlin_Function0):PuzzleSolver_KLong;cdecl;
                                test : function (boardSize:PuzzleSolver_KInt; maxQueueSize:PuzzleSolver_KInt; randomMoveCount:PuzzleSolver_KInt; repeatCount:PuzzleSolver_KInt):PuzzleSolver_kref_com_emc_symmwin_puzzle_TestResults;cdecl;
                                Solver : record
                                    _type : function :PPuzzleSolver_KType;cdecl;
                                    _instance : function :PuzzleSolver_kref_com_emc_symmwin_puzzle_Solver;cdecl;
                                    solve : function (thiz:PuzzleSolver_kref_com_emc_symmwin_puzzle_Solver; puzzle:PuzzleSolver_kref_com_emc_symmwin_puzzle_Solver_Board; maxQueueSize:PuzzleSolver_KInt):PuzzleSolver_kref_com_emc_symmwin_puzzle_Solver_Move;cdecl;
                                    Board : record
                                        _type : function :PPuzzleSolver_KType;cdecl;
                                        Board : function (board:PuzzleSolver_kref_com_emc_symmwin_puzzle_Solver_Board):PuzzleSolver_kref_com_emc_symmwin_puzzle_Solver_Board;cdecl;
                                        distanceFromSolution : function (thiz:PuzzleSolver_kref_com_emc_symmwin_puzzle_Solver_Board):PuzzleSolver_KInt;cdecl;
                                        makeMove : procedure (thiz:PuzzleSolver_kref_com_emc_symmwin_puzzle_Solver_Board; move:PuzzleSolver_kref_com_emc_symmwin_puzzle_Solver_Move);cdecl;
                                        number : function (thiz:PuzzleSolver_kref_com_emc_symmwin_puzzle_Solver_Board; row:PuzzleSolver_KInt; column:PuzzleSolver_KInt):PuzzleSolver_KInt;cdecl;
                                        possibleMoves : function (thiz:PuzzleSolver_kref_com_emc_symmwin_puzzle_Solver_Board; parent:PuzzleSolver_kref_com_emc_symmwin_puzzle_Solver_Move):PuzzleSolver_kref_kotlin_collections_List;cdecl;
                                        tryMove : function (thiz:PuzzleSolver_kref_com_emc_symmwin_puzzle_Solver_Board; row:PuzzleSolver_KInt; column:PuzzleSolver_KInt):PuzzleSolver_KBoolean;cdecl;
                                        Companion : record
                                            _type : function :PPuzzleSolver_KType;cdecl;
                                            _instance : function :PuzzleSolver_kref_com_emc_symmwin_puzzle_Solver_Board_Companion;cdecl;
                                            getBoard : function (thiz:PuzzleSolver_kref_com_emc_symmwin_puzzle_Solver_Board_Companion; size:PuzzleSolver_KInt; randomMoveCount:PuzzleSolver_KInt):PuzzleSolver_kref_com_emc_symmwin_puzzle_Solver_Board;cdecl;
                                          end;
                                      end;
                                    Move : record
                                        _type : function :PPuzzleSolver_KType;cdecl;
                                        Move : function (row:PuzzleSolver_KInt; column:PuzzleSolver_KInt; parent:PuzzleSolver_kref_com_emc_symmwin_puzzle_Solver_Move):PuzzleSolver_kref_com_emc_symmwin_puzzle_Solver_Move;cdecl;
                                        get_column : function (thiz:PuzzleSolver_kref_com_emc_symmwin_puzzle_Solver_Move):PuzzleSolver_KInt;cdecl;
                                        get_parent : function (thiz:PuzzleSolver_kref_com_emc_symmwin_puzzle_Solver_Move):PuzzleSolver_kref_com_emc_symmwin_puzzle_Solver_Move;cdecl;
                                        get_row : function (thiz:PuzzleSolver_kref_com_emc_symmwin_puzzle_Solver_Move):PuzzleSolver_KInt;cdecl;
                                      end;
                                  end;
                                TestResults : record
                                    _type : function :PPuzzleSolver_KType;cdecl;
                                    TestResults : function (elapsedTime:PuzzleSolver_KLong; found:PuzzleSolver_KInt; notFound:PuzzleSolver_KInt):PuzzleSolver_kref_com_emc_symmwin_puzzle_TestResults;cdecl;
                                    get_elapsedTime : function (thiz:PuzzleSolver_kref_com_emc_symmwin_puzzle_TestResults):PuzzleSolver_KLong;cdecl;
                                    get_found : function (thiz:PuzzleSolver_kref_com_emc_symmwin_puzzle_TestResults):PuzzleSolver_KInt;cdecl;
                                    get_notFound : function (thiz:PuzzleSolver_kref_com_emc_symmwin_puzzle_TestResults):PuzzleSolver_KInt;cdecl;
                                  end;
                              end;
                          end;
                      end;
                  end;
              end;
          end;
      end;

PPuzzleSolver_ExportedSymbols  = ^PuzzleSolver_ExportedSymbols;

var
  PuzzleSolver_symbols: function: PPuzzleSolver_ExportedSymbols; cdecl;

implementation

uses
  SysUtils,
  Windows;

  var
    hlib : HMODULE;

    procedure FreePuzzleSolver;
    begin
      FreeLibrary(hlib);
      PuzzleSolver_symbols := nil;
    end;

  procedure LoadPuzzleSolver(lib : system.pchar);
    begin
      FreePuzzleSolver;
      hlib := LoadLibrary(lib);
      if hlib = 0 then
        raise Exception.Create(format('Could not load library: %s',[lib]));
      @PuzzleSolver_symbols := GetProcAddress(hlib,'PuzzleSolver_symbols');
    end;

initialization
  LoadPuzzleSolver('../MultiPlatformPuzzleSolver/build/bin/mingw/releaseShared/PuzzleSolver.dll');
  
finalization
  FreePuzzleSolver;

end.
