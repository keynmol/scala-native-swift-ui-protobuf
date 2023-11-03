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
  const ByteArray *error;
} Result;

typedef void *(*Allocator)(size_t size);

typedef struct Context {
  const Allocator allocator;
} Context;

Result *scala_app_request(const ByteArray *message, const Context *context);
Result *scala_app_init(const ByteArray *start_state, const Context *context);

bool scala_app_result_ok(const Result *result);

#ifndef SN_SKIP_INIT
int ScalaNativeInit(void);
#endif

#endif /* interface_h */
