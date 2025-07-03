package Esport_Website.service;

import java.util.Optional;

import Esport_Website.dto.ReactRequest;
import Esport_Website.entity.React;

public interface ReactService {

	React changeReact(ReactRequest request);

	Optional<React> checkReact(ReactRequest request);

}
