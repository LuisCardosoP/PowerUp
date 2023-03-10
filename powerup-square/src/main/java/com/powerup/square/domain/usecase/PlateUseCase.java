package com.powerup.square.domain.usecase;

import com.powerup.square.application.dto.PlateListRequest;
import com.powerup.square.domain.api.IPlateServicePort;
import com.powerup.square.domain.spi.IPlatePersistencePort;
import com.powerup.square.domain.model.Plate;

import java.util.List;
public class PlateUseCase implements IPlateServicePort {
    private final IPlatePersistencePort platePersistencePort;
    public PlateUseCase(IPlatePersistencePort platePersistencePort) {
        this.platePersistencePort = platePersistencePort;
    }
    @Override
    public void savePlate(Plate plate) {
        platePersistencePort.savePlate(plate);
    }
    @Override
    public List<Plate> getAllPlates() {
        return platePersistencePort.getAllPlates();
    }
    @Override
    public Plate getPlate(Long id) {
        return platePersistencePort.getPlate(id);
    }
    @Override
    public void updatePlate(Plate plate) {
        platePersistencePort.updatePlate(plate);
    }
    @Override
    public void deletePlate(Long id) {
        platePersistencePort.deletePlate(id);
    }

    @Override
    public List<Plate> getPlatesRestaurant(PlateListRequest plateListRequest) {
        return platePersistencePort.getPlatesRestaurant(plateListRequest);
    }


}

