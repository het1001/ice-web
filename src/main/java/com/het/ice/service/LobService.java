package com.het.ice.service;

import com.het.ice.model.Lob;
import com.het.ice.service.template.Result;

public interface LobService {

	Result<Void> create(Lob lob);

	Result<Void> update(Lob lob);

	Result<Void> delete(long lobId);
}
