#ifndef interface_h
#define interface_h

#include <stdbool.h>
#include <stddef.h>

typedef struct ByteArray {
  const int size;
  const char *bytes;
} ByteArray;

typedef struct Result {
  const ByteArray *message;
  const int id;
  const bool isError;
} Result;

Result *scala_app_request(const ByteArray *message);
Result *scala_app_init(const ByteArray *start_state, const ByteArray* options);
bool scala_app_free_result(const Result* result);

bool scala_app_result_ok(const Result *result);

#ifndef SN_SKIP_INIT
int ScalaNativeInit(void);
#endif

#endif /* interface_h */
