#ifndef KONAN_PUZZLESOLVER_H
#define KONAN_PUZZLESOLVER_H
#ifdef __cplusplus
extern "C" {
#endif
#ifdef __cplusplus
typedef bool            PuzzleSolver_KBoolean;
#else
typedef _Bool           PuzzleSolver_KBoolean;
#endif
typedef unsigned short     PuzzleSolver_KChar;
typedef signed char        PuzzleSolver_KByte;
typedef short              PuzzleSolver_KShort;
typedef int                PuzzleSolver_KInt;
typedef long long          PuzzleSolver_KLong;
typedef unsigned char      PuzzleSolver_KUByte;
typedef unsigned short     PuzzleSolver_KUShort;
typedef unsigned int       PuzzleSolver_KUInt;
typedef unsigned long long PuzzleSolver_KULong;
typedef float              PuzzleSolver_KFloat;
typedef double             PuzzleSolver_KDouble;
//typedef float __attribute__ ((__vector_size__ (16))) PuzzleSolver_KVector128;
typedef void*              PuzzleSolver_KNativePtr;
struct PuzzleSolver_KType;
typedef struct PuzzleSolver_KType PuzzleSolver_KType;

typedef struct {
  PuzzleSolver_KNativePtr pinned;
} PuzzleSolver_kref_kotlin_Byte;
typedef struct {
  PuzzleSolver_KNativePtr pinned;
} PuzzleSolver_kref_kotlin_Short;
typedef struct {
  PuzzleSolver_KNativePtr pinned;
} PuzzleSolver_kref_kotlin_Int;
typedef struct {
  PuzzleSolver_KNativePtr pinned;
} PuzzleSolver_kref_kotlin_Long;
typedef struct {
  PuzzleSolver_KNativePtr pinned;
} PuzzleSolver_kref_kotlin_Float;
typedef struct {
  PuzzleSolver_KNativePtr pinned;
} PuzzleSolver_kref_kotlin_Double;
typedef struct {
  PuzzleSolver_KNativePtr pinned;
} PuzzleSolver_kref_kotlin_Char;
typedef struct {
  PuzzleSolver_KNativePtr pinned;
} PuzzleSolver_kref_kotlin_Boolean;
typedef struct {
  PuzzleSolver_KNativePtr pinned;
} PuzzleSolver_kref_kotlin_Unit;
typedef struct {
  PuzzleSolver_KNativePtr pinned;
} PuzzleSolver_kref_com_emc_symmwin_puzzle_Solver;
typedef struct {
  PuzzleSolver_KNativePtr pinned;
} PuzzleSolver_kref_com_emc_symmwin_puzzle_Solver_Board;
typedef struct {
  PuzzleSolver_KNativePtr pinned;
} PuzzleSolver_kref_kotlin_collections_List;
typedef struct {
  PuzzleSolver_KNativePtr pinned;
} PuzzleSolver_kref_com_emc_symmwin_puzzle_Solver_Move;
typedef struct {
  PuzzleSolver_KNativePtr pinned;
} PuzzleSolver_kref_com_emc_symmwin_puzzle_Solver_Board_Companion;


typedef struct {
  /* Service functions. */
  void (*DisposeStablePointer)(PuzzleSolver_KNativePtr ptr);
  void (*DisposeString)(const char* string);
  PuzzleSolver_KBoolean (*IsInstance)(PuzzleSolver_KNativePtr ref, const PuzzleSolver_KType* type);
  PuzzleSolver_kref_kotlin_Byte (*createNullableByte)(PuzzleSolver_KByte);
  PuzzleSolver_kref_kotlin_Short (*createNullableShort)(PuzzleSolver_KShort);
  PuzzleSolver_kref_kotlin_Int (*createNullableInt)(PuzzleSolver_KInt);
  PuzzleSolver_kref_kotlin_Long (*createNullableLong)(PuzzleSolver_KLong);
  PuzzleSolver_kref_kotlin_Float (*createNullableFloat)(PuzzleSolver_KFloat);
  PuzzleSolver_kref_kotlin_Double (*createNullableDouble)(PuzzleSolver_KDouble);
  PuzzleSolver_kref_kotlin_Char (*createNullableChar)(PuzzleSolver_KChar);
  PuzzleSolver_kref_kotlin_Boolean (*createNullableBoolean)(PuzzleSolver_KBoolean);
  PuzzleSolver_kref_kotlin_Unit (*createNullableUnit)(void);

  /* User functions. */
  struct {
    struct {
      struct {
        struct {
          struct {
            struct {
              PuzzleSolver_KLong (*getSystemTimeInMillis)();
              void (*main)();
              struct {
                PuzzleSolver_KType* (*_type)(void);
                PuzzleSolver_kref_com_emc_symmwin_puzzle_Solver (*_instance)();
                PuzzleSolver_kref_kotlin_collections_List (*solve)(PuzzleSolver_kref_com_emc_symmwin_puzzle_Solver thiz, PuzzleSolver_kref_com_emc_symmwin_puzzle_Solver_Board puzzle);
                struct {
                  PuzzleSolver_KType* (*_type)(void);
                  PuzzleSolver_kref_com_emc_symmwin_puzzle_Solver_Board (*Board)(PuzzleSolver_kref_com_emc_symmwin_puzzle_Solver_Board board);
                  PuzzleSolver_KInt (*compareTo)(PuzzleSolver_kref_com_emc_symmwin_puzzle_Solver_Board thiz, PuzzleSolver_kref_com_emc_symmwin_puzzle_Solver_Board board);
                  PuzzleSolver_KInt (*distanceFromSolution)(PuzzleSolver_kref_com_emc_symmwin_puzzle_Solver_Board thiz);
                  PuzzleSolver_kref_com_emc_symmwin_puzzle_Solver_Board (*makeMove)(PuzzleSolver_kref_com_emc_symmwin_puzzle_Solver_Board thiz, PuzzleSolver_kref_com_emc_symmwin_puzzle_Solver_Move move);
                  PuzzleSolver_KInt (*number)(PuzzleSolver_kref_com_emc_symmwin_puzzle_Solver_Board thiz, PuzzleSolver_KInt row, PuzzleSolver_KInt column);
                  PuzzleSolver_kref_kotlin_collections_List (*possibleMoves)(PuzzleSolver_kref_com_emc_symmwin_puzzle_Solver_Board thiz);
                  struct {
                    PuzzleSolver_KType* (*_type)(void);
                    PuzzleSolver_kref_com_emc_symmwin_puzzle_Solver_Board_Companion (*_instance)();
                    PuzzleSolver_kref_com_emc_symmwin_puzzle_Solver_Board (*getBoard)(PuzzleSolver_kref_com_emc_symmwin_puzzle_Solver_Board_Companion thiz, PuzzleSolver_KInt size, PuzzleSolver_KInt randomMoveCount);
                  } Companion;
                } Board;
                struct {
                  PuzzleSolver_KType* (*_type)(void);
                  PuzzleSolver_kref_com_emc_symmwin_puzzle_Solver_Move (*Move)(PuzzleSolver_KInt row, PuzzleSolver_KInt column);
                  PuzzleSolver_KInt (*get_column)(PuzzleSolver_kref_com_emc_symmwin_puzzle_Solver_Move thiz);
                  PuzzleSolver_KInt (*get_row)(PuzzleSolver_kref_com_emc_symmwin_puzzle_Solver_Move thiz);
                } Move;
              } Solver;
            } puzzle;
          } symmwin;
        } emc;
      } com;
    } root;
  } kotlin;
} PuzzleSolver_ExportedSymbols;
extern PuzzleSolver_ExportedSymbols* PuzzleSolver_symbols(void);
#ifdef __cplusplus
}  /* extern "C" */
#endif
#endif  /* KONAN_PUZZLESOLVER_H */
