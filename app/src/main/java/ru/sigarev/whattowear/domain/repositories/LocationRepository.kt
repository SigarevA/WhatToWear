package ru.sigarev.whattowear.domain.repositories

import kotlinx.coroutines.flow.Flow
import ru.sigarev.whattowear.data.db.objects.Location

interface LocationRepository {
    val locations : Flow<List<Location>>
}